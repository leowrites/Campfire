package user.votehelpful;

import entity.Review;
import user.votehelpful.exceptions.ReviewNotFoundException;

public class HelpfulInteractor implements IHelpfulInputBoundary {
    private final IHelpfulDataAccess dataAccess;

    public HelpfulInteractor(IHelpfulDataAccess d) {
        this.dataAccess = d;
    }

    @Override
    public HelpfulResponseModel create(HelpfulRequestModel requestModel) {
        boolean isHelpful = requestModel.getIsHelpful();
        String reviewID = requestModel.getReviewID();
        Review review;

        try {
            review = dataAccess.getReview(reviewID);
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
