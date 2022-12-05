package user.postreview;

import service.ServerStatus;

/** A response model for the postreview use case that frames the output data into an object.
 * Holds the ServerStatus status of the addReviewToCorporate method in PostReview in status,
 * and the String message to go with that status in message.
 */
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
