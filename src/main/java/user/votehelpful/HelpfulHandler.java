package user.votehelpful;

import entity.Review;

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

    public static int performOperation(int i, String operation) {
        if (operation.equals("Subtraction")) {
            return i - 1;
        }
        else {
            return i + 1;
        }
    }

    public static Review updateCount(VoteDecision vote, Review review, String operation) {
        if (vote.equals(VoteDecision.HELPFUL)) {
            int numLikes = review.getNumLikes();
            review.setNumLikes(HelpfulHandler.performOperation(numLikes, operation));
        }
        if (vote.equals(VoteDecision.UNHELPFUL)) {
            int numDislikes = review.getNumDislikes();
            review.setNumDislikes(HelpfulHandler.performOperation(numDislikes, operation));
        }
        return review;

    }
}
