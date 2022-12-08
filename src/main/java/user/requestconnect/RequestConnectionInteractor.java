package user.requestconnect;

import entity.User;
import service.dao.IUserDAO;
import service.ServerStatus;
import user.requestconnect.exceptions.*;


public class RequestConnectionInteractor implements IRequestConnectionInput {
    private final IUserDAO dataAccess;
    public RequestConnectionInteractor(IUserDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * @param requestModel a request model that contains the principal userID and target userID
     * @return a response model with success/error status
     * Error will be returned to the response model if user is not found or users are already connected,
     * success will be returned otherwise
     */
    @Override
    public RequestConnectionResponseModel requestConnection(RequestConnectionRequestModel requestModel){
        String userId = requestModel.getUserId();
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
                        user.getConnections(),
                        userId,
                        targetId,
                        Action.OUTGOING_CONNECT_REQUEST),
                new RequestConnectionUserResponseModel(
                        String.format("You have a connection request from %s", userId),
                        ServerStatus.SUCCESS,
                        target.getIncomingConnectionRequests(),
                        target.getOutgoingConnectionRequests(),
                        target.getConnections(),
                        targetId,
                        userId,
                        Action.INCOMING_CONNECT_REQUEST)
        );
    }
}
