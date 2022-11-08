package user.connect;
import entity.User;
import user.connect.exceptions.PendingRequestExistsException;
import user.connect.exceptions.UserAlreadyConnectedException;

public class ConnectionVerifier {
    private final User user;
    private final User target;
    public ConnectionVerifier(User user, User target) {
        this.user = user;
        this.target = target;
    }

    /**
     * @return if two users are already connected
     */
    public boolean checkAlreadyConnected() throws UserAlreadyConnectedException {
        if (target.getConnections().contains(user.getId())){
            throw new UserAlreadyConnectedException("You are already connected!");
        } else {
            return false;
        }
    }

    /**
     * @return if the user already has an outgoing request
     */
    public boolean checkPendingRequest() throws PendingRequestExistsException {
        if (target.getPendingConnections().contains(user.getId())){
            throw new PendingRequestExistsException("Pending request!");
        } else {
            return false;
        }
    }

    /**
     * @return if the request passes all checks
     */
    public boolean verify() throws UserAlreadyConnectedException, PendingRequestExistsException {
        checkAlreadyConnected();
        checkPendingRequest();
        return true;
    }
}
