package user.deletevote;

import service.ServerStatus;

public class DeleteVoteResponseModel {
    private final ServerStatus status;
    private final String message;

    public DeleteVoteResponseModel(ServerStatus status, String message) {
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
