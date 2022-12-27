package usecases.deletereview;

/**
 * The DeleteReviewRequestModel class represents a request to delete a review.
 * It contains information about the internship for which the review was written and the review itself.
 */
public class DeleteReviewRequestModel {

    private final int internshipId;

    private final int reviewId;

    private String userId;

    public DeleteReviewRequestModel(int internshipId,
                                    int reviewId,
                                    String userId){
        this.internshipId = internshipId;
        this.reviewId = reviewId;
        this.userId = userId;
    }

    public int getInternshipId(){
        return this.internshipId;
    }

    public int getReviewId(){
        return this.reviewId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
