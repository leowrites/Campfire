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

    /**
     * The UserResponseModel if the connection is successful
     * @param message is the message sent by the principal user
     * @param serverStatus should be success
     * @param incomingConnectionRequests is an ArrayList of incoming requests
     * @param outgoingConnectionRequests is an ArrayList of outgoing requests
     * @param connections is an ArrayList of the connected users' userID
     * @param userId is the principal user's userID
     * @param targetId is the target user's userID
     * @param action is one of the action types from the enum class
     */
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

    /**
     * Overloads the previous one when the connection fails
     * @param message is the message sent by the principal user
     * @param serverStatus should be error
     */
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
    }
}
