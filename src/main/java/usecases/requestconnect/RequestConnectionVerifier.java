package usecases.requestconnect;
import entity.User;
import usecases.requestconnect.exceptions.IncomingRequestException;
import usecases.requestconnect.exceptions.PendingRequestExistsException;
import usecases.requestconnect.exceptions.UserAlreadyConnectedException;
import usecases.requestconnect.exceptions.UserConnectSelf;

import java.util.List;
import java.util.stream.Collectors;

public class RequestConnectionVerifier {
    private final User user;
    private final User target;
    public RequestConnectionVerifier(User user, User target) {
        this.user = user;
        this.target = target;
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

    /**
     * raises error if two users are already connected
     @throws UserAlreadyConnectedException if user and target are already connected
     */
    public void checkAlreadyConnected() throws UserAlreadyConnectedException {
        if (checkContains(target.getConnectedUsers(), user.getUsername())){
            throw new UserAlreadyConnectedException("You are already connected!");
        }
    }

    /**
     * raises error if user has an outgoing request
     @throws PendingRequestExistsException if user already has a sent pending request
     */
    public void checkPendingRequest() throws PendingRequestExistsException {
        if (checkContains(target.getIncomingConnectionRequests(), user.getUsername())) {
            throw new PendingRequestExistsException("Pending request!");
        }
    }

    /**
     * @throws UserConnectSelf if user tries to connect themselves
     */
    public void checkConnectSelf() throws UserConnectSelf {
        if (target.getUsername().equals(user.getUsername())) {
            throw new UserConnectSelf("You cannot connect yourself!");
        }
    }

    /**
     * @throws IncomingRequestException throws exception if there is an incoming request
     */
    public void checkIncomingRequest() throws IncomingRequestException {
        if (checkContains(user.getIncomingConnectionRequests(), target.getUsername())) {
            throw new IncomingRequestException(String.format("You have a request from %s", target.getUsername()));
        }
    }

    /**
     * verify the connection and raise errors if a condition is not met
     * @throws UserAlreadyConnectedException if user and target are already connected
     * @throws PendingRequestExistsException if user already has a sent pending request
     */
    public void verify() throws UserAlreadyConnectedException, PendingRequestExistsException, UserConnectSelf, IncomingRequestException {
        checkAlreadyConnected();
        checkPendingRequest();
        checkConnectSelf();
        checkIncomingRequest();
    }
}
