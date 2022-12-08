package user.acceptconnect;
import service.ServerStatus;

/** A response model for the acceptconnect use case that frames the output data into an object.
 * Holds a String message to go with the status in message, the ServerStatus status of the connection
 * done in AcceptConnectionInteractor in status, a user response model for the user in
 * userResponseModel, and a user response model for the target user in targetResponseModel.
 */
public class AcceptConnectionResponseModel {
    private final String message;
    private final ServerStatus serverStatus;
    private AcceptConnectionUserResponseModel userResponseModel;
    private AcceptConnectionUserResponseModel targetResponseModel;

    /** A constructor for AcceptConnectionResponseModel that takes in all four parameters,
     * serverStatus, message, userResponseModel and targetResponseModel.
     * @param serverStatus the ServerStatus status
     * @param message the message to go with the status
     * @param userResponseModel the user's user response model
     * @param targetResponseModel the target's user response model
     */
    public AcceptConnectionResponseModel(ServerStatus serverStatus,
                                          String message,
                                          AcceptConnectionUserResponseModel userResponseModel,
                                          AcceptConnectionUserResponseModel targetResponseModel) {
        this.serverStatus = serverStatus;
        this.message = message;
        this.userResponseModel = userResponseModel;
        this.targetResponseModel = targetResponseModel;
    }

    /** A constructor for AcceptConnectionResponseModel that takes in only two parameters,
     * serverStatus and message.
     * @param serverStatus the ServerStatus status
     * @param message the message to go with the status
     */
    public AcceptConnectionResponseModel(ServerStatus serverStatus, String message) {
        this.message = message;
        this.serverStatus = serverStatus;
    }

    public String getMessage() {
        return message;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public AcceptConnectionUserResponseModel getUserResponseModel() {
        return userResponseModel;
    }

    public AcceptConnectionUserResponseModel getTargetResponseModel() {
        return targetResponseModel;
    }
}
