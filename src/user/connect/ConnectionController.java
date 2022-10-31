package user.connect;
import user.connect.ConnectionRequestModel;
import user.connect.ConnectionResponseModel;
import user.connect.ConnectionInput;

public class ConnectionController {
    final ConnectionInput interactor;
    public ConnectionController(ConnectionInput interactor){
        this.interactor = interactor;
    }
    public ConnectionResponseModel createConnectionRequestModel(String userName, String targetName){
        ConnectionRequestModel requestModel = new ConnectionRequestModel(userName, targetName);
        // fix this
        return interactor.createConnectionResponseModel(requestModel);
    }
}
