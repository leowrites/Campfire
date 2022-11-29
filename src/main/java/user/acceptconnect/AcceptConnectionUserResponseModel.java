package user.acceptconnect;

import service.ServerStatus;
import user.requestconnect.Action;

import java.util.ArrayList;

public class AcceptConnectionUserResponseModel {
    private final String message;
    private final ServerStatus serverStatus;
    private final ArrayList<String> incomingConnectionRequests;
    private final ArrayList<String> outgoingConnectionRequests;
    private final ArrayList<String> connections;
    private final String userId;
    private final String targetId;
    private Action action;

    public AcceptConnectionUserResponseModel(String message, ServerStatus serverStatus,
                                              ArrayList<String> incomingConnectionRequests,
                                              ArrayList<String> outgoingConnectionRequests,
                                              ArrayList<String> connections, String userId,
                                              String targetId, Action action) {
        this.serverStatus = serverStatus;
        this.message = message;
        this.userId = userId;
        this.targetId = targetId;
        this.incomingConnectionRequests = incomingConnectionRequests;
        this.outgoingConnectionRequests = outgoingConnectionRequests;
        this.connections = connections;
        this.action = action;
    }

    public String getMessage() {
        return message;
    }
    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public ArrayList<String> getIncomingConnectionRequests() {
        return incomingConnectionRequests;
    }

    public ArrayList<String> getOutgoingConnectionRequests() {
        return outgoingConnectionRequests;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }

    public String getUserId() {
        return userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public Action getAction() {
        return action;
    }
}
