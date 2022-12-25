package usecases.votehelpful;
import service.ServerStatus;

/** A response model for the votehelpful use case that frames the output data into an object.
 * Holds the ServerStatus status of the voting operation done in HelpdulInteractor in status,
 * and the String message to go with that status in message.
 */
public class HelpfulResponseModel {
    private final ServerStatus status;
    private final String message;
    private final int[] votes;

    public HelpfulResponseModel(ServerStatus status, String message, int[] votes) {
        this.status = status;
        this.message = message;
        this.votes = votes;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public int[] getVotes() {
        return this.votes;
    }
}