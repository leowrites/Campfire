package user.requestconnect;

/**
 * implemented in the RequestConnectionInteractor
 *
 */
public interface IRequestConnectionInput {
    /**
     * specifies the RequestConnectionInteractor to have the requestConnection method
     * when being implemented
     * Creates a RequestConnectionResponseModel using the input from requestModel
     * @param requestModel holds the connection request with the user ID and the target user ID
     * @return the RequestConnectionResponseModel, which holds the result of the connection request
     */

    RequestConnectionResponseModel requestConnection(RequestConnectionRequestModel requestModel);
}
