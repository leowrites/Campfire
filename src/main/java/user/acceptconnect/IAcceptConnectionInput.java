package user.acceptconnect;

/** An interface that is intended to be implemented by the AcceptConnectionInteractor class.
 * Holds one method, acceptConnection, that must be implemented in all classes that implement
 * this interface.
 */
public interface IAcceptConnectionInput {

    /** Creates an AcceptConnectionResponseModel using the input from requestModel
     * @param requestModel an AcceptConnectionRequestModel
     * @return an AcceptConnectionResponseModel
     */
    AcceptConnectionResponseModel acceptConnection(AcceptConnectionRequestModel requestModel);
}
