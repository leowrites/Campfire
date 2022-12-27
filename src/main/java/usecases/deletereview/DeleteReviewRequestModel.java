package usecases.deletereview;

import java.util.UUID;

/**
 * The DeleteReviewRequestModel class represents a request to delete a review.
 * It contains information about the internship for which the review was written and the review itself.
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
