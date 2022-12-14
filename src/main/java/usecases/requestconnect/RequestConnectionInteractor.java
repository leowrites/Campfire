package usecases.requestconnect;

import entity.User;
import service.dao.IUserDAO;
import service.ServerStatus;
import usecases.requestconnect.exceptions.*;


public class RequestConnectionInteractor implements IRequestConnectionInput {
    private final IUserDAO dataAccess;
    public RequestConnectionInteractor(IUserDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * @param requestModel a request model that contains target
     * @return a response model
     */
    @Override
    public RequestConnectionResponseModel requestConnection(RequestConnectionRequestModel requestModel){
        String userId = requestModel.getUsername();
        String targetId = requestModel.getTargetId();
        User user;
        User target;

        try {
            user = dataAccess.getUser(userId);
            target = dataAccess.getUser(targetId);
        } catch (UserNotFoundException e) {
            return new RequestConnectionResponseModel(ServerStatus.ERROR, new RequestConnectionUserResponseModel(
                    e.getMessage(),
                    ServerStatus.ERROR
            ), null);
        }

        RequestConnectionVerifier verifier = new RequestConnectionVerifier(user, target);

        try {
            verifier.verify();
        } catch (UserAlreadyConnectedException | PendingRequestExistsException | UserConnectSelf |
                 IncomingRequestException e) {
            // prepare failure response model for already connected
            return new RequestConnectionResponseModel(ServerStatus.ERROR, new RequestConnectionUserResponseModel(
                    e.getMessage(),
                    ServerStatus.ERROR
            ), null);
        }

        RequestConnectionHandler handler = new RequestConnectionHandler(user, target, dataAccess);

        handler.sendConnectionRequestToTarget();

        return new RequestConnectionResponseModel(ServerStatus.SUCCESS,
                new RequestConnectionUserResponseModel(
                        String.format("You sent a connection request to %s", targetId),
                        ServerStatus.SUCCESS,
                        user.getIncomingConnectionRequests(),
                        user.getOutgoingConnectionRequests(),
                        user.getConnectedUsers(),
                        userId,
                        targetId,
                        Action.OUTGOING_CONNECT_REQUEST),
                new RequestConnectionUserResponseModel(
                        String.format("You have a connection request from %s", userId),
                        ServerStatus.SUCCESS,
                        target.getIncomingConnectionRequests(),
                        target.getOutgoingConnectionRequests(),
                        target.getConnectedUsers(),
                        targetId,
                        userId,
                        Action.INCOMING_CONNECT_REQUEST)
        );
    }
}
