package user.acceptconnect;

public class AcceptConnectionController {
    final IAcceptConnectionInput interactor;
    public AcceptConnectionController(IAcceptConnectionInput interactor){
        this.interactor = interactor;
    }
    public AcceptConnectionResponseModel acceptConnection(String userName, String targetName){
        AcceptConnectionRequestModel requestModel = new AcceptConnectionRequestModel(userName, targetName);
        return interactor.acceptConnection(requestModel);
    }
}
