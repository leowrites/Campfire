package user.connect;

import entity.User;

public class ConnectionHandler {

    private final User user;
    private final User target;
    private final IConnectionDataAccess dataAccess;
    public ConnectionHandler(User user, User target, IConnectionDataAccess dataAccess) {
        this.user = user;
        this.target = target;
        this.dataAccess = dataAccess;
    }

    public void acceptConnectionRequest() {
        user.getConnections().add(user.getId());
        target.getConnections().add(user.getId());
        dataAccess.saveUser(user);
        dataAccess.saveUser(target);
    }
    public void sendConnectionRequestToTarget() {
        user.getConnectionRequests().add(target.getId());
        target.getPendingConnections().add(user.getId());
        dataAccess.saveUser(user);
        dataAccess.saveUser(target);
    }
}
