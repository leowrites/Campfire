package user.connect;

public class ConnectionRequestModel {
    public String userName;
    public String targetName;

    public ConnectionRequestModel(String userName, String targetName) {
        this.userName = userName;
        this.targetName = targetName;
    }

    public String getUserName() {
        return userName;
    }
    public String getTargetName() {
        return targetName;
    }
}
