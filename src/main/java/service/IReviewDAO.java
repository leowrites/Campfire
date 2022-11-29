package service;

import entity.Review;

import java.util.ArrayList;

public interface IReviewDAO {
    /**
     * Gets the review given the review id
     * @param reviewId the id of the review
     * @return a review object
     */
    Review getReview(String reviewId);

    ArrayList<Review> getAllReviews();

    /**
     * Creates a new review in the db given a review object
     * @param review the review to be saved
     * @return the id of the created review
     */
    String saveReview(Review review);
}
