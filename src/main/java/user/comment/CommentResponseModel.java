package user.comment;
import service.ServerStatus;

public class CommentResponseModel {
    private final ServerStatus status;
    private final String message;
    private final int commentId;
    private final String datePosted;

    public CommentResponseModel(ServerStatus status, String message, int commentId, String datePosted) {
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
    
    public String getDatePosted() {
        return this.datePosted;
    }

}
