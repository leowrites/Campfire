package service.dao;

import entity.Review;

import java.util.ArrayList;

public interface IReviewDAO {
    /**
     * Gets the review given the review id
     * @param reviewId the id of the review
     * @return a review object
     */
    Review getReview(int reviewId);

    ArrayList<Review> getAllReviews();

    /**
     * Creates a new review in the db given a review object
     * @param review the review to be saved
     * @return the id of the created review
     */
    String saveReview(Review review);

    /**
     * Updates a review
     * @param review the new review object
     * @param reviewId the id of the review
     */
    void updateReview(Review review, int reviewId);

    /**
     * Deletes a review.
     * @param reviewId the id of the comment to be deleted
     */
    void deleteReview(int reviewId);
}
