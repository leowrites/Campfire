package usecases.acceptconnect;

import entity.User;
import service.dao.IUserDAO;
import service.ServerStatus;
import usecases.acceptconnect.exceptions.NoRequestFoundException;
import usecases.requestconnect.Action;
import usecases.requestconnect.exceptions.UserAlreadyConnectedException;
import usecases.requestconnect.exceptions.UserNotFoundException;

/** The acceptconnect use case interactor that calls the acceptConnection method from the
 * IAcceptConnectionInput input boundary. When initialized, takes in an object that implements
 * IUserDAO to access the user database through.
 */
public class AcceptConnectionInteractor implements IAcceptConnectionInput {
    private final IUserDAO dataAccess;

    public AcceptConnectionInteractor(IUserDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    /** Accepts the connection from the target user as specified by the inputs in requestModel and
     * updates the user database.
     * @param requestModel a request model that contains target
     * @return a response model to be sent back to the client
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
            return new AcceptConnectionResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        AcceptConnectionHandler acceptConnectionHandler = new AcceptConnectionHandler(user, target, dataAccess);

        try {
            acceptConnectionHandler.acceptConnection();
        } catch (NoRequestFoundException | UserAlreadyConnectedException e) {
            return new AcceptConnectionResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        return new AcceptConnectionResponseModel(ServerStatus.SUCCESS, "Success",
                new AcceptConnectionUserResponseModel(
                        String.format("You are connected with %s", targetId),
                        ServerStatus.SUCCESS,
                        user.getIncomingConnectionRequests(),
                        user.getOutgoingConnectionRequests(),
                        user.getConnectedUsers(),
                        userId,
                        targetId,
                        Action.OUTGOING_CONNECT_ACCEPT),
                new AcceptConnectionUserResponseModel(
                        String.format("You are connected with %s", userId),
                        ServerStatus.SUCCESS,
                        target.getIncomingConnectionRequests(),
                        target.getOutgoingConnectionRequests(),
                        target.getConnectedUsers(),
                        targetId,
                        userId,
                        Action.INCOMING_CONNECT_ACCEPT)
        );
    }
}
