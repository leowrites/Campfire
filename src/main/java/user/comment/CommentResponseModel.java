package user.comment;
import service.ServerStatus;

/** A response model for the comment use case that frames the output data into an object.
 * Holds the ServerStatus status of the create method in CommentInteractor in status,
 * and the String message to go with that status in message.
 */
public class CommentResponseModel {
    private final ServerStatus status;
    private final String message;

    public CommentResponseModel(ServerStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
