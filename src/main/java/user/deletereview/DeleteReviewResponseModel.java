package user.deletereview;

/** A response model for the deletereview use case that frames the output data into an object.
 * Holds the String message about the status of the deletion of the review.
 */
public class DeleteReviewResponseModel {
    private final String message;

    public DeleteReviewResponseModel(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
