package user.acceptconnect;
import service.ServerStatus;

public class AcceptConnectionResponseModel {
    private final String message;
    private final ServerStatus serverStatus;
    private AcceptConnectionUserResponseModel userResponseModel;
    private AcceptConnectionUserResponseModel targetResponseModel;

    public AcceptConnectionResponseModel(ServerStatus serverStatus,
                                          String message,
                                          AcceptConnectionUserResponseModel userResponseModel,
                                          AcceptConnectionUserResponseModel targetResponseModel) {
        this.serverStatus = serverStatus;
        this.message = message;
        this.userResponseModel = userResponseModel;
        this.targetResponseModel = targetResponseModel;
    }

    public AcceptConnectionResponseModel(ServerStatus serverStatus, String message) {
        this.message = message;
        this.serverStatus = serverStatus;
    }

    public String getMessage() {
        return message;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public AcceptConnectionUserResponseModel getUserResponseModel() {
        return userResponseModel;
    }

    public AcceptConnectionUserResponseModel getTargetResponseModel() {
        return targetResponseModel;
    }
}
