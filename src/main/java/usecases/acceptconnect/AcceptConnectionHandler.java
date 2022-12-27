package usecases.acceptconnect;

import entity.User;
import service.dao.IUserDAO;
import usecases.acceptconnect.exceptions.NoRequestFoundException;
import usecases.requestconnect.exceptions.UserAlreadyConnectedException;

import java.util.List;
import java.util.stream.Collectors;

/** A class in the acceptconnect use case that handles the connection between the user user and
 *  the target user target and updates the user database through an object that implements IUserDAO,
 *  all of which are taken in on initialization.
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
     * Check if the given list of users contains a user with the given username.
     *
     * @param listToBeChecked the list of users to be checked
     * @param targetUsername the username to search for in the list of users
     * @return true if the list contains a user with the given username, false otherwise
     */
    public boolean checkContains(List<User> listToBeChecked, String targetUsername) {
        List<User> result = listToBeChecked.stream()
                .filter(u -> u.getUsername().equals(targetUsername))
                .collect(Collectors.toList());
        return !result.isEmpty();
    }

    /** Accepts the connection between user and target, and updates the database through dataAccess.
     * @throws UserAlreadyConnectedException thrown when the users are already connected
     * @throws NoRequestFoundException thrown when no request from the target user is found
     */
    public void acceptConnection() throws UserAlreadyConnectedException, NoRequestFoundException {
        if (checkContains(user.getConnectedUsers(), target.getUsername())) {
            // throw already connected error
            throw new UserAlreadyConnectedException(String.format("%s and %s are already connected!",
                    user.getUsername(), target.getUsername()));
        }
        if (!checkContains(user.getIncomingConnectionRequests(), target.getUsername())) {
            // throw no request from target
            throw new NoRequestFoundException(String.format("No request from %s found!", target.getUsername()));
        }
        List<User> userIncomingConnectionRequestsUser = user.getIncomingConnectionRequests();
        List<User>filteredIncomingConnectionRequestsUser = userIncomingConnectionRequestsUser
                .stream()
                .filter(u -> !u.getUsername().equals(target.getUsername()))
                        .collect(Collectors.toList());
        user.setIncomingConnectionRequests(filteredIncomingConnectionRequestsUser);

        List<User> userConnections = user.getConnectedUsers();
        userConnections.add(target);
        user.setConnectedUsers(userConnections);

        List<User> targetOutgoingConnectionRequestsUser = target.getOutgoingConnectionRequests();
        List<User> filteredOutgoingConnectionRequestsUser = targetOutgoingConnectionRequestsUser
                .stream()
                .filter(u -> !u.getUsername().equals(user.getUsername()))
                .collect(Collectors.toList());
        target.setOutgoingConnectionRequests(filteredOutgoingConnectionRequestsUser);

        List<User> targetConnections = target.getConnectedUsers();
        targetConnections.add(user);
        target.setConnectedUsers(targetConnections);

        dataAccess.updateUser(user);
        dataAccess.updateUser(target);
    }
}
