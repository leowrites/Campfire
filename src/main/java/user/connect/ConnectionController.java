package user.connect;

public class ConnectionController {
    final IConnectionInput interactor;
    public ConnectionController(IConnectionInput interactor){
        this.interactor = interactor;
    }
    public ConnectionResponseModel requestConnection(String userId, String targetId){
        ConnectionRequestModel requestModel = new ConnectionRequestModel(userId, targetId);
        return interactor.requestConnection(requestModel);
    }
}
