package user.connect;
import user.connect.ConnectionRequestModel;
import user.connect.ConnectionResponseModel;

public interface ConnectionInput {

    boolean checkInputIsValid(String userName, String targetName);
    boolean checkUserTargetAlreadyConnected(String userName, String targetName);
    boolean sendConnectionRequestToTarget(String userName, String targetName);
    ConnectionResponseModel createConnectionResponseModel(ConnectionRequestModel requestModel);
}
