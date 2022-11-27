package user.comment;
import service.ServerStatus;

import java.util.ArrayList;

public class CommentResponseModel {
    private ServerStatus status;
    private String message;
    private ArrayList<String> comments;

    public CommentResponseModel(ServerStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommentResponseModel(ServerStatus status, String message, ArrayList<String> comments) {
        this.status = status;
        this.message = message;
        this.comments = comments;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public ArrayList<String> getComments() {
        return this.comments;
    }
}
