package user.requestconnect;

import entity.User;
import service.IUserDataAccess;
import service.ServerStatus;
import user.requestconnect.exceptions.PendingRequestExistsException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import user.requestconnect.exceptions.UserConnectSelf;
import user.requestconnect.exceptions.UserNotFoundException;


public class RequestConnectionInteractor implements IRequestConnectionInput {
    private final IUserDataAccess dataAccess;
    public RequestConnectionInteractor(IUserDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * @param requestModel a request model that contains target
     * @return a response model
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
            return new RequestConnectionResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        RequestConnectionVerifier verifier = new RequestConnectionVerifier(user, target);

        try {
            verifier.verify();
        } catch (UserAlreadyConnectedException | PendingRequestExistsException | UserConnectSelf e) {
            // prepare failure response model for already connected
            return new RequestConnectionResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        RequestConnectionHandler handler = new RequestConnectionHandler(user, target, dataAccess);

        if (verifier.checkIncomingRequest()) {
            handler.acceptConnectionRequest();
        } else {
            handler.sendConnectionRequestToTarget();
        }

        return new RequestConnectionResponseModel(ServerStatus.SUCCESS, "Success",
                new RequestConnectionUserResponseModel(
                        String.format("You sent a connection request to %s", targetId),
                        ServerStatus.SUCCESS,
                        user.getIncomingConnectionRequests(),
                        user.getOutgoingConnectionRequests(),
                        user.getConnections(),
                        userId,
                        targetId),
                new RequestConnectionUserResponseModel(
                        String.format("You have a connection request from %s", userId),
                        ServerStatus.SUCCESS,
                        target.getIncomingConnectionRequests(),
                        target.getOutgoingConnectionRequests(),
                        target.getConnections(),
                        targetId,
                        userId)
        );
    }
}
