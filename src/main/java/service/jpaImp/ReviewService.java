package service.jpaImp;

import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import service.dao.IReviewDAO;
import usecases.comment.exceptions.ReviewNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class ReviewService implements IReviewDAO {
    @Autowired
    ReviewRepository reviewRepository;
    /**
     * Gets the review given the review id
     *
     * @param reviewId the id of the review
     * @return a review object
     */
    @Override
    public Review getReview(int reviewId) {
        return null;
    }

    @Override
    public Review getReview(UUID reviewID) throws ReviewNotFoundException {
        Optional<Review> review = reviewRepository.findById(reviewID);
        if (review.isPresent()) {
            return review.get();
        } else {
            throw new ReviewNotFoundException("No review found!");
        }
    }

    /**
     * Creates a new review in the db given a review object
     *
     * @param review the review to be saved
     * @return the id of the created review
     */
    @Override
    public int saveReview(Review review) {
        return 0;
    }

    @Override
    public int saveReview(Review review, int internshipId) {
        return 0;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Updates a review
     *
     * @param review   the new review object
     * @param reviewId the id of the review
     */
    @Override
    public void updateReview(Review review, int reviewId) {
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Deletes a review.
     *
     * @param reviewId the id of the comment to be deleted
     */
    @Override
    public void deleteReview(int reviewId) {

    }

    @Override
    public void delete(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
    }


    @Override
    public List<Review> getReviewsByInternship(int internshipId) throws ReviewNotFoundException {
        return null;
    }

    @Override
    public List<Review> getReviewsByInternship(UUID internshipId) throws ReviewNotFoundException {
        Review exampleReview = new Review();
        exampleReview.setInternshipId(internshipId);
        Example<Review> example = Example.of(exampleReview,
                ExampleMatcher.matching().withIgnorePaths("user", "datePosted", "numLikes",
                        "numDislikes", "content", "comments", "rating", "id", "votedUsers"));
        return reviewRepository.findAll(example);
    }
}
