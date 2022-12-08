package usecases.requestconnect;

public class RequestConnectionRequestModel {
    private final String userId;
    private final String targetId;

    public RequestConnectionRequestModel(String userId, String targetId) {
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
