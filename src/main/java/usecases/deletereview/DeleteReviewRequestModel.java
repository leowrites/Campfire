package usecases.deletereview;

import java.util.UUID;

/** A request model for the deletereview use case that frames the input data into an object.
 * Holds an int representation of the id of the internship with the review, an int representation
 * of the id of the review being deleted, and a String representation of the id of the user
 * deleting the review.
 */
public class DeleteReviewRequestModel {

    private final UUID internshipId;

    private final UUID reviewId;

    private String userId;

    public DeleteReviewRequestModel(UUID internshipId,
                                    UUID reviewId,
                                    String userId){
        this.internshipId = internshipId;
        this.reviewId = reviewId;
        this.userId = userId;
    }

    public UUID getInternshipId(){
        return this.internshipId;
    }

    public UUID getReviewId(){
        return this.reviewId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
