package user.connect;

import entity.User;

public class ConnectionHandler {

    private final User user;
    private final User target;
    private final IConnectionDataAccess dataAccess;
    private final IConnectionSocket socket;
    public ConnectionHandler(User user, User target, IConnectionDataAccess dataAccess, IConnectionSocket socket) {
        this.user = user;
        this.target = target;
        this.dataAccess = dataAccess;
        this.socket = socket;
    }

    public void acceptConnectionRequest() {
        user.getConnections().add(user.getId());
        target.getConnections().add(user.getId());
        dataAccess.saveUser(user);
        dataAccess.saveUser(target);
        // broadcast update to target
        socket.broadcastConnectionRequest();
    }
    public void sendConnectionRequestToTarget() {
        user.getConnectionRequests().add(target.getId());
        target.getPendingConnections().add(user.getId());
        dataAccess.saveUser(user);
        dataAccess.saveUser(target);
        // broadcast update to target
        socket.broadcastConnectionRequest();
    }
}
