package usecases.postreview;

/** An interface that is intended to be implemented by the PostReview class.
 * Holds one method, addReviewToCorporate, that must be implemented in all classes that
 * implement this interface.
 */
public interface IPostReview {
    /** Creates a PostReviewResponse using the input from request.
     * @param request the request model
     * @return a response model to be sent back to the client
     */
    PostReviewResponse addReviewToInternship(PostReviewRequest request);
}
