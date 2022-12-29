package usecases.requestconnect;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import entity.User;
import service.ServerStatus;
import java.util.List;

public class RequestConnectionUserResponseModel {
    private final String message;
    private final ServerStatus serverStatus;
    @JsonIncludeProperties("username")
    private List<User> incomingConnectionRequests;

    @JsonIncludeProperties("username")
    private List<User> outgoingConnectionRequests;

    @JsonIncludeProperties("username")
    private List<User> connections;
    private String userId;
    private String targetId;
    private Action action;

    public RequestConnectionUserResponseModel(String message, ServerStatus serverStatus,
                                              List<User> incomingConnectionRequests,
                                              List<User> outgoingConnectionRequests,
                                              List<User> connections, String userId,
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

    public List<User> getIncomingConnectionRequests() {
        return incomingConnectionRequests;
    }

    public List<User> getOutgoingConnectionRequests() {
        return outgoingConnectionRequests;
    }

    public List<User> getConnections() {
        return connections;
    }

    public String getUserId() {
        return userId;
    }

    public Action getAction() {
        return action;
    }
}
