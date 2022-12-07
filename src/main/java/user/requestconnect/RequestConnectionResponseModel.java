package user.requestconnect;
import service.ServerStatus;

public class RequestConnectionResponseModel {
    private final ServerStatus serverStatus;
    private final RequestConnectionUserResponseModel userResponseModel;
    private final RequestConnectionUserResponseModel targetResponseModel;

    /**
     * The responseModel includes the connection results, and will be passed to the db
     * @param serverStatus is either error or success, indicates if the connections is processed successfully
     * @param userResponseModel is the ResponseModel of the principal user
     * @param targetResponseModel is the ResponseModel of the target user
     */

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
