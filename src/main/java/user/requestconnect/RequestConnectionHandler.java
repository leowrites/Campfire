package user.requestconnect;

import entity.User;

public class RequestConnectionHandler {

    private final User user;
    private final User target;
    private final IRequestConnectionDataAccess dataAccess;
    private final IRequestConnectionSocket socket;
    public RequestConnectionHandler(User user, User target, IRequestConnectionDataAccess dataAccess, IRequestConnectionSocket socket) {
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
