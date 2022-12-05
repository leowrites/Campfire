package user.createinternship;

import service.ServerStatus;

/** A response model for the createinternship use case that frames the output data into an object.
 * Holds the ServerStatus status of the internship creation done in CreateInternshipInteractor in
 * status, and the String message to go with that status in message.
 */
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
