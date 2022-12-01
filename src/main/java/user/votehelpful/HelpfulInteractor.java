package user.votehelpful;

import entity.Review;
import service.dao.IReviewDAO;
import user.votehelpful.exceptions.ReviewNotFoundException;

public class HelpfulInteractor implements IHelpfulInputBoundary {
    private final IReviewDAO reviewDAO;

    public HelpfulInteractor(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

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
            return new HelpfulResponseModel("Failure");
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

        return new HelpfulResponseModel("Success");
    }
}
