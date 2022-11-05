package user.connect;

public class ConnectionInteractor implements IConnectionInput {
    public ConnectionRequestModel requestModel;
    public IConnectionDataAccess dataAccess;

    public ConnectionInteractor(ConnectionRequestModel requestModel,
                                IConnectionDataAccess dataAccess) {
        this.requestModel = requestModel;
        this.dataAccess = dataAccess;
    }

    /**
     * @param userId the id of the current user
     * @param targetId the userId of the target user
     * @return if the inputs are valid ids
     */
    @Override
    public boolean checkInputIsValid(String userId, String targetId
    ) {
        return false;
    }

    /**
     * @param userId the id of the current user
     * @param targetId
     *
     * @return
     */
    @Override
    public boolean checkUserTargetAlreadyConnected(String userId, String targetId
    ) {
        return false;
    }

    /**
     * @param userId
     * @param targetId
     *
     * @return
     */
    @Override
    public boolean sendConnectionRequestToTarget(String userId, String targetId
    ) {
        return false;
    }

    /**
     * @param requestModel a request model that contains target
     * @return a response model
     */
    @Override
    public ConnectionResponseModel createConnectionResponseModel(ConnectionRequestModel requestModel) {
        return null;
    }
}
