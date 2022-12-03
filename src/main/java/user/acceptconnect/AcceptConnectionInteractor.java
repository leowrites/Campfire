package user.acceptconnect;

import entity.User;
import service.dao.IUserDAO;
import service.ServerStatus;
import user.acceptconnect.exceptions.NoRequestFoundException;
import user.requestconnect.Action;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import user.requestconnect.exceptions.UserNotFoundException;

public class AcceptConnectionInteractor implements IAcceptConnectionInput {
    private final IUserDAO dataAccess;

    public AcceptConnectionInteractor(IUserDAO dataAccess) {
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
                        user.getConnections(),
                        userId,
                        targetId,
                        Action.OUTGOING_CONNECT_ACCEPT),
                new AcceptConnectionUserResponseModel(
                        String.format("You are connected with %s", userId),
                        ServerStatus.SUCCESS,
                        target.getIncomingConnectionRequests(),
                        target.getOutgoingConnectionRequests(),
                        target.getConnections(),
                        targetId,
                        userId,
                        Action.INCOMING_CONNECT_ACCEPT)
        );
    }
}
