package user.requestconnect;

/**
 * The inputBoundary of the requestConnect use case, which is implemented in the RequestConnectionInteractor
 */
public interface IRequestConnectionInput {
    /**
     * The input boundary specifies the RequestConnectionInteractor to have the requestConnection method
     * when implementing this input boundary
     * @param requestModel holds the connection request with the user ID and the target user ID
     * @return the RequestConnectionResponseModel, which holds the result of the connection request
     */

    RequestConnectionResponseModel requestConnection(RequestConnectionRequestModel requestModel);
}
