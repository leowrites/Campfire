package user.connect;

public class ConnectionRequestModel {
    public String userId;
    public String targetId;

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
