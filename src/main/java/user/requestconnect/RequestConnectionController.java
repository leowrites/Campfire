package user.requestconnect;

public class RequestConnectionController {
    final IRequestConnectionInput interactor;
    public RequestConnectionController(IRequestConnectionInput interactor){
        this.interactor = interactor;
    }
    public RequestConnectionResponseModel requestConnection(String userName, String targetName){
        RequestConnectionRequestModel requestModel = new RequestConnectionRequestModel(userName, targetName);
        return interactor.requestConnection(requestModel);
    }
}
