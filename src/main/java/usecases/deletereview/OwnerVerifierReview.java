package usecases.deletereview;

import entity.Review;
import usecases.exceptions.NotOwnReviewException;

/** A class in the deletereview use case that checks if the user is the owner of the review, and
 * throws a NotOwnReviewException if the review is not owned by the user.
 */
public class OwnerVerifierReview {

    private final Review review;

    private final String userId;

    public OwnerVerifierReview(Review review, String userId){
        this.review = review;
        this.userId = userId;
    }

    /** Verifies if review is owned by the user whose id is userId.
     * @throws NotOwnReviewException thrown when the review does not belong to the user
     */
    public void verify() throws NotOwnReviewException {
        // Raises error if the two ids are not equal to each other (Comment does not belong to user)
        String userIdComment = this.review.getUserId();
        if (!this.userId.equals(userIdComment)) {
            throw new NotOwnReviewException("Review does not belong to user");
        }

    }
}
