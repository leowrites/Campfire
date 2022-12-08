package user.requestconnect;

public class RequestConnectionRequestModel {
    private final String userId;
    private final String targetId;

    /**
     * request model for the connection request
     * @param userId is the userID of the principal who sends the request
     * @param targetId is the userID of the target user receives the request
     */

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
