package usecases.comment;
import java.util.Date;
import java.util.UUID;

import service.ServerStatus;

/** A response model for the comment use case that frames the output data into an object.
 * Holds the ServerStatus status of the create method in CommentInteractor in status,
 * the String message to go with that status in message, the id representation of the comment in id,
 * and the date the comment was posted in datePosted.
 */
public class CommentResponseModel {
    private final ServerStatus status;
    private final String message;
    private final UUID id;
    private final Date datePosted;

    public CommentResponseModel(ServerStatus status, String message, UUID commentId, Date datePosted) {
        this.status = status;
        this.message = message;
        this.id = commentId;
        this.datePosted = datePosted;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getDatePosted() {return this.datePosted;}
    public UUID getId() {
        return this.id;
    }

}
