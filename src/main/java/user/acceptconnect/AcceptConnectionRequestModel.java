package user.acceptconnect;

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
