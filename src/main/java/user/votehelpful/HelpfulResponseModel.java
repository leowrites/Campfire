package user.votehelpful;
import service.ServerStatus;

public class HelpfulResponseModel {
    private final ServerStatus status;
    private final String message;

    public HelpfulResponseModel(ServerStatus status, String message) {
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