package user.comment;
import java.util.Date;

import service.ServerStatus;

public class CommentResponseModel {
    private final ServerStatus status;
    private final String message;
    private final int commentId;
    private final Date datePosted;

    public CommentResponseModel(ServerStatus status, String message, int commentId, Date datePosted) {
        this.status = status;
        this.message = message;
        this.commentId = commentId;
        this.datePosted = datePosted;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCommentId() {
        return this.commentId;
    }
    
    public Date getDatePosted() {
        return this.datePosted;
    }

}
