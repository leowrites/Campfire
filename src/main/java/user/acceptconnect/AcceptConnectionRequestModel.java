package user.acceptconnect;

/** A request model for the acceptconnect use case that frames the input data into an object. Holds
 * a String representation of the user's id in userId and String representation of the target user's
 * id in targetId.
 */
public class AcceptConnectionRequestModel {
    private final String userId;
    private final String targetId;

    public AcceptConnectionRequestModel(String userId, String targetId) {
        this.userId = userId;
        this.targetId = targetId;
    }

    public String getUserId() {
        return userId;
    }
    public String getTargetId() {
        return targetId;
    }
}
