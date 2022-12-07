package user.votehelpful;

public class HelpfulHandler {

    public static VoteDecision toVoteDecision(String isHelpful) {
        switch (isHelpful) {
            case "Helpful":
                return VoteDecision.HELPFUL;
            case "Unhelpful":
                return VoteDecision.UNHELPFUL;
            default:
                return VoteDecision.NONE;
        }
    }
}
