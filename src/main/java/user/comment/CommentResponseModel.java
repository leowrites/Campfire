package user.comment;
import service.ServerStatus;

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
