package user.requestconnect;

import entity.User;
import service.IUserDataAccess;

import java.util.ArrayList;

public class RequestConnectionHandler {

    private final User user;
    private final User target;
    private final IUserDataAccess dataAccess;
    public RequestConnectionHandler(User user, User target, IUserDataAccess dataAccess) {
        this.user = user;
        this.target = target;
        this.dataAccess = dataAccess;
    }

    public void acceptConnectionRequest() {
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
