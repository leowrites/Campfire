package user.createinternship;

import service.ServerStatus;

public class CreateInternshipResponseDS {
    private final ServerStatus serverStatus;
    private final String message;

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public String getMessage() {
        return message;
    }

    public CreateInternshipResponseDS(ServerStatus serverStatus, String message) {
        this.serverStatus = serverStatus;
        this.message = message;
    }
}
