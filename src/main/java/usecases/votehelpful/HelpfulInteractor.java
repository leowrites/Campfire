package usecases.votehelpful;

import entity.Review;
import service.ServerStatus;
import service.dao.IReviewDAO;
import usecases.votehelpful.exceptions.ReviewNotFoundException;

import java.util.HashMap;
import java.util.UUID;

/** The votehelpful use case interactor that calls the create method from the
 * IHelpfulInputBoundary input boundary. When initialized, takes in an object that
 * implements IReviewDAO to access the database through.
 */
public class HelpfulInteractor implements IHelpfulInputBoundary {
    private final IReviewDAO reviewDAO;

    public HelpfulInteractor(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    /** Increases the count of helpful or unhelpful by 1.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public HelpfulResponseModel create(HelpfulRequestModel requestModel) {
        UUID reviewId = requestModel.getReviewId();
        Review review;

        try {
            review = reviewDAO.getReview(reviewId);
            if (review == null) {
                throw new ReviewNotFoundException("Review does not exist.");
            }
        }
        catch (ReviewNotFoundException e) {
            return new HelpfulResponseModel(ServerStatus.ERROR, e.getMessage(), VoteDecision.NONE);
        }

        String userId = requestModel.getUserId();
        String isHelpful = requestModel.getIsHelpful();
        VoteDecision newVote = HelpfulHandler.toVoteDecision(isHelpful);
        HashMap<String, VoteDecision> votedUsers = review.getVotedUsers();
        VoteDecision previousVote;

        boolean userHasVoted = votedUsers.containsKey(userId);

        if (userHasVoted) {
            previousVote = votedUsers.get(userId);
            if (newVote.equals(previousVote)) {
                HelpfulHandler.updateCount(newVote, review, "Subtraction");
                votedUsers.remove(userId);
                newVote = VoteDecision.NONE;
            }
            else {
                HelpfulHandler.updateCount(previousVote, review, "Subtraction");
                HelpfulHandler.updateCount(newVote, review, "Addition");
                votedUsers.replace(userId, newVote);
            }
        }
        else {
            votedUsers.put(userId, newVote);
            HelpfulHandler.updateCount(newVote, review, "Addition");
        }

        review.setVotedUsers(votedUsers);
        reviewDAO.update(review);

        return new HelpfulResponseModel(ServerStatus.SUCCESS, "Vote received.", newVote);
    }
}
