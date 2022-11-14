package user.requestconnect;

import entity.User;

import java.util.ArrayList;

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
        ArrayList<String> userConnections = user.getConnections();
        ArrayList<String> targetConnections = target.getConnections();

        userConnections.add(target.getId());
        targetConnections.add(user.getId());

        target.setConnections(targetConnections);
        user.setConnections(userConnections);

        dataAccess.saveUser(user);
        dataAccess.saveUser(target);
        // broadcast update to target
        socket.broadcastConnectionRequest();
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
        // broadcast update to target
        socket.broadcastConnectionRequest();
    }
}
