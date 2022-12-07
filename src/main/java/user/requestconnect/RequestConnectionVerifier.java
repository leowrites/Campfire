package user.requestconnect;
import entity.User;
import user.requestconnect.exceptions.IncomingRequestException;
import user.requestconnect.exceptions.PendingRequestExistsException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import user.requestconnect.exceptions.UserConnectSelf;

public class RequestConnectionVerifier {
    private final User user;
    private final User target;
    public RequestConnectionVerifier(User user, User target) {
        this.user = user;
        this.target = target;
    }

    /**
     * raises error if two users are already connected
     @throws UserAlreadyConnectedException if user and target are already connected
     */
    public void checkAlreadyConnected() throws UserAlreadyConnectedException {
        if (target.getConnections().contains(user.getUsername())){
            throw new UserAlreadyConnectedException("You are already connected!");
        }
    }

    /**
     * raises error if user has an outgoing request
     @throws PendingRequestExistsException if user already has a sent pending request
     */
    public void checkPendingRequest() throws PendingRequestExistsException {
        if (target.getIncomingConnectionRequests().contains(user.getUsername())){
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
        if (user.getIncomingConnectionRequests().contains(target.getUsername())) {
            throw new IncomingRequestException(String.format("You have a request from %s", target.getUsername()));
        }
    }

    /**
     * the verifier verifies different types of potential errors and throws exceptions when necessary
     * @throws UserAlreadyConnectedException if user and target are already connected
     * @throws PendingRequestExistsException if user already has a sent pending request
     * @throws UserConnectSelf if user attempts to connect itself
     * @throws IncomingRequestException if there is an incoming request
     */
    public void verify() throws UserAlreadyConnectedException, PendingRequestExistsException, UserConnectSelf, IncomingRequestException {
        checkAlreadyConnected();
        checkPendingRequest();
        checkConnectSelf();
        checkIncomingRequest();
    }
}
