package usecases.requestconnect;

public class RequestConnectionRequestModel {
    private String username;
    private final String targetId;

    public RequestConnectionRequestModel(String targetId) {
        this.targetId = targetId;
    }

    public RequestConnectionRequestModel(String username, String targetId) {
        this.username = username;
        this.targetId = targetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTargetId() {
        return targetId;
    }
}
