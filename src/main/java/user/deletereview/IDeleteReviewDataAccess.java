package user.deletereview;

import entity.Review;

import java.util.ArrayList;

public interface IDeleteReviewDataAccess {

    Review getReview(String Id);

    ArrayList<Review> getReviews();

    /** Updates the reviews.
     * @param reviews an ArrayList of Reviews
     */
    void updateReviews(ArrayList<Review> reviews);

}
