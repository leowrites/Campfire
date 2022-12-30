package usecases.acceptconnect;

/** A request model for the acceptconnect use case that frames the input data into an object. Holds
 * a String representation of the user's id in username and String representation of the target user's
 * id in targetId.
 */
public class AcceptConnectionRequestModel {
    private String username;
    private final String targetId;

    public AcceptConnectionRequestModel(String username, String targetId) {
        this.username = username;
        this.targetId = targetId;
    }

    public AcceptConnectionRequestModel(String targetId) {
        this.targetId = targetId;
    }

    public String getUsername() {
        return username;
    }
    public String getTargetId() {
        return targetId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
