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
        ArrayList<String> userConnectionRequests = user.getConnectionRequests();
        userConnectionRequests.remove(target.getUsername());
        user.setConnectionRequests(userConnectionRequests);

        ArrayList<String> userConnections = user.getConnections();
        userConnections.add(target.getUsername());
        user.setConnectionRequests(userConnections);

        ArrayList<String> targetPendingConnections = target.getPendingConnections();
        targetPendingConnections.remove(target.getUsername());
        target.setPendingConnections(targetPendingConnections);

        ArrayList<String> targetConnections = target.getConnections();
        targetConnections.add(user.getUsername());
        target.setConnectionRequests(targetConnections);

        dataAccess.updateUser(user);
        dataAccess.updateUser(target);
    }
    public void sendConnectionRequestToTarget() {
        ArrayList<String> userConnectionRequests = user.getConnectionRequests();
        ArrayList<String> targetPendingConnections = target.getPendingConnections();

        userConnectionRequests.add(target.getUsername());
        targetPendingConnections.add(user.getUsername());

        user.setConnectionRequests(userConnectionRequests);
        target.setPendingConnections(targetPendingConnections);

        dataAccess.updateUser(user);
        dataAccess.updateUser(target);
    }
}
