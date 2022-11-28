package service;

import entity.Review;

public interface IReviewDAO {
    /**
     * Gets the review given the review id
     * @param reviewId the id of the review
     * @return a review object
     */
    Review getReview(String reviewId);

    /**
     * Creates a new review in the db given a review object
     * @param review the review to be saved
     * @return the id of the created review
     */
    long saveReview(Review review);
}
