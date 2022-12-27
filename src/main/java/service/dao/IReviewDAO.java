package service.dao;

import entity.Review;
import usecases.comment.exceptions.ReviewNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IReviewDAO {
    /**
     * Gets the review given the review id
     * @param reviewId the id of the review
     * @return a review object
     */
    Review getReview(int reviewId);

    Review getReview(UUID reviewID) throws ReviewNotFoundException;

    /**
     * Creates a new review in the db given a review object
     * @param review the review to be saved
     * @return the id of the created review
     */
    int saveReview(Review review);

    int saveReview(Review review, int internshipId);

    Review save(Review review);
    /**
     * Updates a review
     * @param review the new review object
     * @param reviewId the id of the review
     */
    void updateReview(Review review, int reviewId);

    Review update(Review review);

    /**
     * Deletes a review.
     * @param reviewId the id of the comment to be deleted
     */
    void deleteReview(int reviewId);

    void delete(UUID reviewId);
    List<Review> getReviewsByInternship(int internshipId) throws ReviewNotFoundException;
    List<Review> getReviewsByInternship(UUID internshipId) throws ReviewNotFoundException;
}
