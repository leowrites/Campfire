package user.connect;

import entity.User;
import user.connect.exceptions.PendingRequestExistsException;
import user.connect.exceptions.UserAlreadyConnectedException;
import user.connect.exceptions.UserNotFoundException;

public class ConnectionInteractor implements IConnectionInput {
    private final IConnectionDataAccess dataAccess;

    public ConnectionInteractor(IConnectionDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * @param requestModel a request model
     * @return a response model
     */
    @Override
    public ConnectionResponseModel requestConnection(ConnectionRequestModel requestModel) {
        String userId = requestModel.getUserId();
        String targetId = requestModel.getTargetId();
        User user;
        User target;

        try {
            user = dataAccess.getUser(userId);
            target = dataAccess.getUser(targetId);
            if (user == null || target == null){
                throw new UserNotFoundException("Could not find target user");
            }
        } catch (UserNotFoundException e) {
            return new ConnectionResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        ConnectionVerifier verifier = new ConnectionVerifier(user, target);

        try {
            verifier.verify();
        } catch (UserAlreadyConnectedException | PendingRequestExistsException e) {
            // prepare failure response model for already connected
            return new ConnectionResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        ConnectionHandler handler = new ConnectionHandler(user, target, dataAccess);

        if (verifier.checkIncomingRequest()) {
            handler.acceptConnectionRequest();
        } else {
            handler.sendConnectionRequestToTarget();
        }

        // updated entities
        return new ConnectionResponseModel(ServerStatus.SUCCESS, user, target);
    }
}
