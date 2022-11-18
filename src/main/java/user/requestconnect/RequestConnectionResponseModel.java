package user.requestconnect;
import entity.User;
import java.util.*;

public class RequestConnectionResponseModel {
    private ServerStatus connectionStatus;
    private String message;
    private ArrayList<String> connectionRequests;
    private ArrayList<String> pendingConnections;
    private ArrayList<String> connections;
    private String userId;
    private String targetId;

    public RequestConnectionResponseModel(ServerStatus connectionStatus, String message, ArrayList<String> connectionRequests,
                                          ArrayList<String> pendingConnections, ArrayList<String> connections, String userId,
                                          String targetId) {
        this.message = message;
        this.userId = userId;
        this.targetId = targetId;
        this.connectionStatus = connectionStatus;
        this.connectionRequests = connectionRequests;
        this.pendingConnections = pendingConnections;
        this.connections = connections;
    }

    public RequestConnectionResponseModel(ServerStatus connectionStatus, String message, String userId) {
        this.userId = userId;
        this.connectionStatus = connectionStatus;
        this.message = message;
    }

    public RequestConnectionResponseModel(String message, String userId) {
        this.userId = userId;
        this.message = message;
    }

    public ServerStatus getConnectionStatus() {
        return connectionStatus;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getConnectionRequests() {
        return connectionRequests;
    }

    public ArrayList<String> getPendingConnections() {
        return pendingConnections;
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
}
