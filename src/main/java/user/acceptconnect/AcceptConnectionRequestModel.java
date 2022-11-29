package user.acceptconnect;

public class AcceptConnectionRequestModel {
    private final String userId;
    private final String targetId;

    public AcceptConnectionRequestModel(String userId, String targetId) {
        this.userId = userId;
        this.targetId = targetId;
    }

    /**
     * returns the user ID
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * returns the target ID
     * @return the target ID
     */
    public String getTargetId() {
        return targetId;
    }
}
