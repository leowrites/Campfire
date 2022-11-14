package user.requestconnect;
import entity.User;
import user.requestconnect.exceptions.PendingRequestExistsException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;

public class RequestConnectionVerifier {
    private final User user;
    private final User target;
    public RequestConnectionVerifier(User user, User target) {
        this.user = user;
        this.target = target;
    }

    /**
     * raises error if two users are arleady connected
     */
    public void checkAlreadyConnected() throws UserAlreadyConnectedException {
        if (target.getConnections().contains(user.getId())){
            throw new UserAlreadyConnectedException("You are already connected!");
        }
    }

    /**
     * raises error if user has an outgoing request
     */
    public void checkPendingRequest() throws PendingRequestExistsException {
        if (target.getPendingConnections().contains(user.getId())){
            throw new PendingRequestExistsException("Pending request!");
        }
    }

    /**
     * @return check if user has an incoming request from target
     */
    public boolean checkIncomingRequest(){
        return user.getConnectionRequests().contains(target.getId());
    }

    /**
     * verify the connection and raise errors if a condition is not met
     */
    public void verify() throws UserAlreadyConnectedException, PendingRequestExistsException {
        checkAlreadyConnected();
        checkPendingRequest();
    }
}
