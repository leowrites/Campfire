package service.dao;

import entity.Review;
import user.comment.exceptions.ReviewNotFoundException;

import java.util.ArrayList;

/** An interface for the Review data access object.
 */
public interface IReviewDAO {

    /** Gets a Review object based on its id.
     * @param reviewId the id of the Review object
     * @return the Review object with id reviewId
     */
    Review getReview(int reviewId);

    /** Gets all the Review objects in the database.
     * @return an ArrayList of all Review objects
     */
    ArrayList<Review> getAllReviews();

    /** Saves a new Review object.
     * @param review the Review object to be stored
     * @return an int representing the id of the Review object
     */
    int saveReview(Review review);

    /** Saves a new Review object.
     * @param review the Review object to be stored
     * @param internshipId the id of the Internship object the review is under
     * @return an int representing the id of the Review
     */
    int saveReview(Review review, int internshipId);

    /** Updates a Review object.
     * @param review the new Review object
     * @param reviewId the id of the Review object to be updated
     */
    void updateReview(Review review, int reviewId);

    /** Deletes a Review object.
     * @param reviewId the id of the Review object to be deleted
     */
    void deleteReview(int reviewId);

    /** Gets all the reviews under an internship given its internshipId.
     * @param internshipId the id of the Internship object the reviews are under
     * @return an ArrayList of Review objects under the parent
     * @throws ReviewNotFoundException thrown when there are no reviews under the internship
     */
    ArrayList<Review> getReviewsByInternship(int internshipId) throws ReviewNotFoundException;
}
