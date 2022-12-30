package usecases.acceptconnect;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import entity.User;
import service.ServerStatus;
import usecases.requestconnect.Action;

import java.util.List;

/** A response model for the acceptconnect use case specificcally for users that frames the output
 * data into an object. Holds a String message to go with the status in message, the ServerStatus
 * status of the connection done in AcceptConnectionInteractor in status, an ArrayList of Strings
 * of the incoming connection requests to the user in incomingConnectionRequests, an ArrayList of
 * Strings of the outgoing connection requests from the user in outgoingConnectionRequests, an
 * ArrayList of Strings of the connections to the user in connections, a String representation of
 * the user's id in userId, a String representation of the target user's id in targetId, and an
 * Action in action.
 */
public class AcceptConnectionUserResponseModel {
    private final String message;
    private final ServerStatus serverStatus;

    @JsonIncludeProperties("username")
    private final List<User> incomingConnectionRequests;
    @JsonIncludeProperties("username")
    private final List<User> outgoingConnectionRequests;
    @JsonIncludeProperties("username")
    private final List<User> connections;
    private final String userId;
    private final String targetId;
    private final Action action;

    public AcceptConnectionUserResponseModel(String message, ServerStatus serverStatus,
                                             List<User> incomingConnectionRequests,
                                             List<User> outgoingConnectionRequests,
                                             List<User> connections, String userId,
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

    public List<User> getIncomingConnectionRequests() {
        return incomingConnectionRequests;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public List<User> getOutgoingConnectionRequests() {
        return outgoingConnectionRequests;
    }

    public List<User> getConnections() {
        return connections;
    }

    public String getTargetId() {
        return targetId;
    }

    public Action getAction() {
        return action;
    }

    public String getUserId() {
        return userId;
    }

}
