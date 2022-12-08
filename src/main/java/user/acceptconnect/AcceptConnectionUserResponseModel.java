package user.acceptconnect;

import service.ServerStatus;
import user.requestconnect.Action;

import java.util.ArrayList;

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
