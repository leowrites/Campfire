package user.connect;
import user.connect.ConnectionInput;
import user.connect.ConnectionRequestModel;

public class ConnectionInteractor implements ConnectionInput{
    public ConnectionRequestModel requestModel;
    public ConnectionInput interactor;
    public ConnectionInteractor(ConnectionInput interactor, ConnectionRequestModel requestModel) {
        this.interactor = interactor;
        this.requestModel = requestModel;
    }

    /**
     * @param userName
     * @param targetName
     * @return
     */
    @Override
    public boolean checkInputIsValid(String userName, String targetName) {
        return false;
    }

    /**
     * @param userName
     * @param targetName
     * @return
     */
    @Override
    public boolean checkUserTargetAlreadyConnected(String userName, String targetName) {
        return false;
    }

    /**
     * @param userName
     * @param targetName
     * @return
     */
    @Override
    public boolean sendConnectionRequestToTarget(String userName, String targetName) {
        return false;
    }

    /**
     * @param requestModel
     * @return
     */
    @Override
    public ConnectionResponseModel createConnectionResponseModel(ConnectionRequestModel requestModel) {
        return null;
    }
}
