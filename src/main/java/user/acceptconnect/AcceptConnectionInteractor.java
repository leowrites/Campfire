package user.acceptconnect;

import entity.User;
import user.acceptconnect.exceptions.NoRequestFoundException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import user.requestconnect.exceptions.UserNotFoundException;

public class AcceptConnectionInteractor implements IAcceptConnectionInput {
    private final IAcceptConnectionDataAccess dataAccess;

    public AcceptConnectionInteractor(IAcceptConnectionDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * @param requestModel a request model that contains target
     * @return a response model
     */
    @Override
    public AcceptConnectionResponseModel acceptConnection(AcceptConnectionRequestModel requestModel){
        String userId = requestModel.getUserId();
        String targetId = requestModel.getTargetId();
        User user;
        User target;
        try {
            user = dataAccess.getUser(userId);
            target = dataAccess.getUser(targetId);
        } catch (UserNotFoundException e) {
            return new AcceptConnectionResponseModel("Failure", e.getMessage());
        }

        AcceptConnectionHandler acceptConnectionHandler = new AcceptConnectionHandler(user, target, dataAccess);

        try {
            acceptConnectionHandler.acceptConnection();
        } catch (NoRequestFoundException | UserAlreadyConnectedException e) {
            return new AcceptConnectionResponseModel("Failure", e.getMessage());
        }

        return new AcceptConnectionResponseModel("Success", "you connected with _");
    }
}
