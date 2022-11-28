package user.postreview;

import service.ServerStatus;

public class PostReviewResponse {
    private final ServerStatus status;
    private final String message;

    public PostReviewResponse(ServerStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}