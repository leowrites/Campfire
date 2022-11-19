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
        ArrayList<String> userConnections = user.getConnections();
        ArrayList<String> targetConnections = target.getConnections();

        userConnections.add(target.getId());
        targetConnections.add(user.getId());

        target.setConnections(targetConnections);
        user.setConnections(userConnections);

        dataAccess.saveUser(user);
        dataAccess.saveUser(target);
    }
    public void sendConnectionRequestToTarget() {
        ArrayList<String> userConnectionRequests = user.getConnectionRequests();
        ArrayList<String> targetPendingConnections = target.getPendingConnections();

        userConnectionRequests.add(target.getId());
        targetPendingConnections.add(user.getId());

        user.setConnectionRequests(userConnectionRequests);
        target.setPendingConnections(targetPendingConnections);

        dataAccess.saveUser(user);
        dataAccess.saveUser(target);
    }
}
