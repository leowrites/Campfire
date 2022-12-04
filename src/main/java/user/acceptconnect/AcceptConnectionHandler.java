package user.acceptconnect;

import entity.User;
import service.dao.IUserDAO;
import user.acceptconnect.exceptions.NoRequestFoundException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import java.util.ArrayList;

/**
 * The handler connects a user with a second target user by checking to see if the users
 * are already connected and that there is a user request.  If neither UserAlreadyAcceptedException
 * nor NoRequestFoundException are thrown, the users are connected.
 */
public class AcceptConnectionHandler {
    private final User user;
    private final User target;
    private final IUserDAO dataAccess;
    public AcceptConnectionHandler(User user, User target, IUserDAO dataAccess) {
        this.user = user;
        this.target = target;
        this.dataAccess = dataAccess;
    }

    /**
     * Creates connection between user and target or throws exception
     * @throws UserAlreadyConnectedException when user and target are already connected
     * @throws NoRequestFoundException when target tries to accept request from user who has not requested them
     */
    public void acceptConnection() throws UserAlreadyConnectedException, NoRequestFoundException {
        if (user.getConnections().contains(target.getUsername())) {
            // throw already connected error
            throw new UserAlreadyConnectedException(String.format("%s and %s are already connected!",
                    user.getUsername(), target.getUsername()));
        }
        if (!user.getIncomingConnectionRequests().contains(target.getUsername())) {
            // throw no request from user
            throw new NoRequestFoundException(String.format("No request from %s found!", target.getUsername()));
        }
        ArrayList<String> userIncomingConnectionRequests = user.getIncomingConnectionRequests();
        userIncomingConnectionRequests.remove(target.getUsername());
        user.setIncomingConnectionRequests(userIncomingConnectionRequests);

        ArrayList<String> userConnections = user.getConnections();
        userConnections.add(target.getUsername());
        user.setConnections(userConnections);

        ArrayList<String> targetOutgoingConnectionRequests = target.getOutgoingConnectionRequests();
        targetOutgoingConnectionRequests.remove(user.getUsername());
        target.setOutgoingConnectionRequests(targetOutgoingConnectionRequests);

        ArrayList<String> targetConnections = target.getConnections();
        targetConnections.add(user.getUsername());
        target.setConnections(targetConnections);

        dataAccess.updateUser(user);
        dataAccess.updateUser(target);
    }
}
