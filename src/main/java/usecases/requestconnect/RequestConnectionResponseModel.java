package usecases.requestconnect;
import service.ServerStatus;

public class RequestConnectionResponseModel {
    private final ServerStatus serverStatus;
    private final RequestConnectionUserResponseModel userResponseModel;
    private final RequestConnectionUserResponseModel targetResponseModel;

    public RequestConnectionResponseModel(ServerStatus serverStatus,
                                          RequestConnectionUserResponseModel userResponseModel,
                                          RequestConnectionUserResponseModel targetResponseModel) {
        this.serverStatus = serverStatus;
        this.userResponseModel = userResponseModel;
        this.targetResponseModel = targetResponseModel;
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
