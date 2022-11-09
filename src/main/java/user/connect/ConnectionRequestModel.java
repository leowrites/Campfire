package user.connect;

public class ConnectionRequestModel {
    private final String userId;
    private final String targetId;

    public ConnectionRequestModel(String userId, String targetId) {
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
