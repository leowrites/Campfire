package user.comment;

/** An interface that is intended to be implemented by the CommentInteractor class.
 * Holds one method, create, that must be implemented in all classes that implement
 * this interface.
 */
public interface ICommentInputBoundary {

    /** Creates a CommentResponseModel using the input from requestModel.
     * @param requestModel a CommentRequestModel
     * @return a CommentResponseModel
     */
    CommentResponseModel create(CommentRequestModel requestModel);
}
