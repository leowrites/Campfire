package user.connect;

public class ConnectionController {
    final IConnectionInput interactor;
    public ConnectionController(IConnectionInput interactor){
        this.interactor = interactor;
    }
    public ConnectionResponseModel createConnectionRequestModel(String userName, String targetName){
        ConnectionRequestModel requestModel = new ConnectionRequestModel(userName, targetName);
        // fix this
        return interactor.createConnectionResponseModel(requestModel);
    }
}
