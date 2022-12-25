package usecases.votehelpful;
import service.ServerStatus;

/** A response model for the votehelpful use case that frames the output data into an object.
 * Holds the ServerStatus status of the voting operation done in HelpdulInteractor in status,
 * and the String message to go with that status in message.
 */
public class HelpfulResponseModel {
    private final ServerStatus status;
    private final String message;
    private final int finalHelpfulVote;
    private final int finalUnhelpfulVote;

    private final VoteDecision voteDecision;

    public HelpfulResponseModel(ServerStatus status,
                                String message,
                                int finalHelpfulVote,
                                int finalUnhelpfulVote,
                                VoteDecision voteDecision) {
        this.status = status;
        this.message = message;
        this.finalHelpfulVote = finalHelpfulVote;
        this.finalUnhelpfulVote = finalUnhelpfulVote;
        this.voteDecision = voteDecision;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public int getFinalHelpfulVote() {
        return finalHelpfulVote;
    }

    public int getFinalUnhelpfulVote() {
        return finalUnhelpfulVote;
    }

    public VoteDecision getVoteDecision() {
        return voteDecision;
    }
}