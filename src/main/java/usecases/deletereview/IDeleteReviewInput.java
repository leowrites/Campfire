package usecases.deletereview;

/** An interface that is intended to be implemented by the DeleteReviewInteractor class.
 * Holds one method, deleteReview, that must be implemented in all classes that implement
 * this interface.
 */
public interface IDeleteReviewInput {
    /** Creates a DeleteReviewRequestModel using the inputs from request_model
     * @param requestModel a DeleteReviewRequestModel
     * @return a DeleteReviewResponseModel
     */
    DeleteReviewResponseModel deleteReview(DeleteReviewRequestModel requestModel);
}
