package user.deletereview;

import entity.Review;
import exceptions.NotOwnReviewException;

public class OwnerVerifierReview {

    private final Review review;

    private final String userId;

    public OwnerVerifierReview(Review review, String userId){
        this.review = review;
        this.userId = userId;
    }

    public void verify() throws NotOwnReviewException {
        // Raises error if the two ids are not equal to each other (Comment does not belong to user)
        String userIdComment = this.review.getUserID();
        if (!this.userId.equals(userIdComment)) {
            throw new NotOwnReviewException("Review does not belong to user");
        }

    }
}
