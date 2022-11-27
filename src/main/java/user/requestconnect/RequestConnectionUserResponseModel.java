package user.requestconnect;

import service.ServerStatus;

import java.util.ArrayList;

public class RequestConnectionUserResponseModel {
    private final String message;
    private final ServerStatus serverStatus;
    private ArrayList<String> incomingConnectionRequests;
    private ArrayList<String> outgoingConnectionRequests;
    private ArrayList<String> connections;
    private String userId;
    private String targetId;
    private Action action;

    public RequestConnectionUserResponseModel(String message, ServerStatus serverStatus,
                                              ArrayList<String> incomingConnectionRequests,
                                              ArrayList<String> outgoingConnectionRequests,
                                              ArrayList<String> connections, String userId,
                                              String targetId, Action action) {
        this.serverStatus = serverStatus;
        this.message = message;
        this.userId = userId;
        this.targetId = targetId;
        this.action = action;
        this.incomingConnectionRequests = incomingConnectionRequests;
        this.outgoingConnectionRequests = outgoingConnectionRequests;
        this.connections = connections;
    }

    public RequestConnectionUserResponseModel(String message, ServerStatus serverStatus) {
        this.message = message;
        this.serverStatus = serverStatus;
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
    };
}