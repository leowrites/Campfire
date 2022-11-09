package user.connect;

import entity.User;
import user.connect.exceptions.PendingRequestExistsException;
import user.connect.exceptions.UserAlreadyConnectedException;
import user.connect.exceptions.UserNotFoundException;

public class ConnectionInteractor implements IConnectionInput {
    public ConnectionRequestModel requestModel;
    public IConnectionDataAccess dataAccess;
    public ConnectionInteractor(ConnectionRequestModel requestModel,
                                IConnectionDataAccess dataAccess) {
        this.requestModel = requestModel;
        this.dataAccess = dataAccess;
    }

    // might move these somewhere else
    /**
     * @param userId the id of the current user
     * @param targetId the id of the target user
     * @return if request was sent successfully
     */
    public boolean sendConnectionRequestToTarget(String userId, String targetId
    ) {
        return false;
    }

    /**
     * @return check if user has a request from target
     */
    public boolean checkIncomingRequest(User user, User target){
        return user.getConnectionRequests().contains(target.getId());
    }

    /**
     * @param requestModel a request model that contains target
     * @return a response model
     */
    @Override
    public ConnectionResponseModel createConnectionResponseModel(ConnectionRequestModel requestModel){
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
            // prepare failure view
            return new ConnectionResponseModel("Failure %s", e.getMessage());
        }
        if (checkIncomingRequest(user, target)) {
            // connect and return
            return new ConnectionResponseModel("Success", String.format("You are connected with %s", target.getName()));
        }
        ConnectionVerifier verifier = new ConnectionVerifier(user, target);
        try {
            verifier.verify();
        } catch (UserAlreadyConnectedException e) {
            // prepare failure view for already connected
        } catch (PendingRequestExistsException e) {
            // prepare failure view for pending request
        }

        // once verified, send connect request

        // pass in
        return null;
    }
}
