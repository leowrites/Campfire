package service;

import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ReviewDAO implements IReviewDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Gets the review given the review id
     * @param reviewId the id of the review
     * @return a review object
     */
    @Override
    public Review getReview(String reviewId) {
        return null;
    }

    /**
     * Creates a new review in the db given a review object
     * @param review the review to be saved
     * @return the id of the created review
     */
    @Override
    public long saveReview(Review review) {
        return 123;
    }
}
