package user.requestconnect;
import entity.User;
import user.requestconnect.exceptions.PendingRequestExistsException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import user.requestconnect.exceptions.UserConnectSelf;

import java.util.Objects;

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
        if (target.getPendingConnections().contains(user.getUsername())){
            throw new PendingRequestExistsException("Pending request!");
        }
    }

    /**
     * @throws UserConnectSelf if user tries to connect themselves
     */
    public void checkConnectSelf() throws UserConnectSelf {
        if (Objects.equals(target.getUsername(), user.getUsername())) {
            throw new UserConnectSelf("You cannot connect yourself!");
        }
    }

    /**
     * @return check if user has an incoming request from target
     */
    public boolean checkIncomingRequest(){
        return user.getConnectionRequests().contains(target.getUsername());
    }

    /**
     * verify the connection and raise errors if a condition is not met
     * @throws UserAlreadyConnectedException if user and target are already connected
     * @throws PendingRequestExistsException if user already has a sent pending request
     */
    public void verify() throws UserAlreadyConnectedException, PendingRequestExistsException, UserConnectSelf {
        checkAlreadyConnected();
        checkPendingRequest();
        checkConnectSelf();
    }
}
