package usecases.requestconnect;

import entity.User;
import service.dao.IUserDAO;

import java.util.ArrayList;

public class RequestConnectionHandler {

    private final User user;
    private final User target;
    private final IUserDAO dataAccess;

    /**
     * The RequestConnectionHandler handles the connection request and updates the conection status for the principal
     * and target users via dataAccess
     * @param user is the principal user who sends the request
     * @param target is the target user who receives the request
     * @param dataAccess is the dataAccess that connects the use case to the db
     */
    public RequestConnectionHandler(User user, User target, IUserDAO dataAccess) {
        this.user = user;
        this.target = target;
        this.dataAccess = dataAccess;
    }

    /**
     * Handles the connection request for principal user sending and the target user receiving
     */
    public void sendConnectionRequestToTarget() {
        ArrayList<String> userOutgoing = user.getOutgoingConnectionRequests();
        ArrayList<String> targetIncoming = target.getIncomingConnectionRequests();

        userOutgoing.add(target.getUsername());
        targetIncoming.add(user.getUsername());

        user.setOutgoingConnectionRequests(userOutgoing);
        target.setIncomingConnectionRequests(targetIncoming);

        dataAccess.updateUser(user);
        dataAccess.updateUser(target);
    }
}
