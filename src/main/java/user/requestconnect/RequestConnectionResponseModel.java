package user.requestconnect;
import service.ServerStatus;

public class RequestConnectionResponseModel {
    private final String message;
    private final ServerStatus serverStatus;
    private RequestConnectionUserResponseModel userResponseModel;
    private RequestConnectionUserResponseModel targetResponseModel;

    public RequestConnectionResponseModel(ServerStatus serverStatus,
                                          String message,
                                          RequestConnectionUserResponseModel userResponseModel,
                                          RequestConnectionUserResponseModel targetResponseModel) {
        this.serverStatus = serverStatus;
        this.message = message;
        this.userResponseModel = userResponseModel;
        this.targetResponseModel = targetResponseModel;
    }

    public RequestConnectionResponseModel(ServerStatus serverStatus, String message) {
        this.message = message;
        this.serverStatus = serverStatus;
    }

    public String getMessage() {
        return message;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public RequestConnectionUserResponseModel getUserResponseModel() {
        return userResponseModel;
    }

    public RequestConnectionUserResponseModel getTargetResponseModel() {
        return targetResponseModel;
    }
}
