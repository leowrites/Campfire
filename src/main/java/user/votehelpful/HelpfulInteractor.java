package user.votehelpful;

import entity.Review;
import entity.User;
import service.ServerStatus;
import service.dao.IReviewDAO;
import user.votehelpful.exceptions.ReviewNotFoundException;

import java.util.ArrayList;

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
        boolean isHelpful = requestModel.getIsHelpful();
        int reviewId = requestModel.getReviewId();
        Review review;

        try {
            review = reviewDAO.getReview(reviewId);
            if (review == null) {
                throw new ReviewNotFoundException("Review does not exist.");
            }
        }
        catch (ReviewNotFoundException e) {
            return new HelpfulResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        String userId = requestModel.getUserId();
        ArrayList<String> votedUsers = review.getVotedUsers();
        if (votedUsers.contains(userId)) {
            return null;
        }
        else {
            votedUsers.add(userId);
            review.setVotedUsers(votedUsers);
        }

        if (isHelpful) {
            int numLikes = review.getNumLikes();
            numLikes = numLikes + 1;
            review.setNumLikes(numLikes);
        }
        else {
            int numDislikes = review.getNumDislikes();
            numDislikes = numDislikes + 1;
            review.setNumDislikes(numDislikes);
        }

        reviewDAO.updateReview(review, reviewId);

        return new HelpfulResponseModel(ServerStatus.SUCCESS, "Vote received.");
    }
}
