package user.comment;
import java.util.Date;

import service.ServerStatus;

public class CommentResponseModel {
    private final ServerStatus status;
    private final String message;
    private final int id;
    private final Date datePosted;

    public CommentResponseModel(ServerStatus status, String message, int commentId, Date datePosted) {
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

    public int getId() {
        return this.id;
    }
    
    public Date getDatePosted() {
        return this.datePosted;
    }

}
