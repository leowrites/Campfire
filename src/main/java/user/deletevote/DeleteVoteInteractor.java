package user.deletevote;

import entity.Review;
import service.dao.IReviewDAO;
import user.comment.exceptions.ReviewNotFoundException;
import user.votehelpful.VoteDecision;
import service.ServerStatus;

import java.util.HashMap;

public class DeleteVoteInteractor implements IDeleteVoteInput {
    private final IReviewDAO reviewDAO;

    public DeleteVoteInteractor(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    public DeleteVoteResponseModel create(DeleteVoteRequestModel requestModel) {
        int reviewId = requestModel.getReviewId();

        Review review;
        try {
            review = reviewDAO.getReview(reviewId);
            if (review == null) {
                throw new ReviewNotFoundException("Review does not exist.");
            }
        } catch (ReviewNotFoundException e) {
            return new DeleteVoteResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        String userId = requestModel.getUserId();
        HashMap<String, VoteDecision> votedUsers = review.getVotedUsers();
        if (!votedUsers.keySet().contains(userId)) {
            return null;
        }

        VoteDecision vote = votedUsers.get(userId);
        if (vote.equals(VoteDecision.HELPFUL)) {
            review.setNumLikes(review.getNumLikes() - 1);
        }
        if (vote.equals(VoteDecision.UNHELPFUL)) {
            review.setNumDislikes(review.getNumDislikes() - 1);
        }

        votedUsers.remove(userId);
        review.setVotedUsers(votedUsers);
        reviewDAO.updateReview(review, reviewId);

        return new DeleteVoteResponseModel(ServerStatus.SUCCESS, "Vote removed.");
    }

}
