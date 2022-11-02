package user.connect;

public interface IConnectionInput {

    boolean checkInputIsValid(String userId, String targetId);
    boolean checkUserTargetAlreadyConnected(String userId, String targetId);
    boolean sendConnectionRequestToTarget(String userId, String targetId);
    ConnectionResponseModel createConnectionResponseModel(ConnectionRequestModel requestModel);
}
