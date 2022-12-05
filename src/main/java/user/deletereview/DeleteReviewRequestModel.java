package user.deletereview;

/** A request model for the deletereview use case that frames the input data into an object.
 * Holds an int representation of the id of the internship with the review, an int representation
 * of the id of the review being deleted, and a String representation of the id of the user
 * deleting the review.
 */
public class DeleteReviewRequestModel {

    private final int internshipId;

    private final int reviewId;

    private final String userId;

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
}
