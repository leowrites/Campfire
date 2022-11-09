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
            return new ConnectionResponseModel("Failure %s", e.getMessage());
        }

        ConnectionVerifier verifier = new ConnectionVerifier(user, target);

        try {
            verifier.verify();
        } catch (UserAlreadyConnectedException e) {
            // prepare failure view for already connected
        } catch (PendingRequestExistsException e) {
            // prepare failure view for pending request
        }

        ConnectionHandler handler = new ConnectionHandler(user, target, dataAccess);

        if (verifier.checkIncomingRequest()) {
            handler.acceptConnectionRequest();
        } else {
            handler.sendConnectionRequestToTarget();
        }

        return new ConnectionResponseModel("Success");
    }
}
