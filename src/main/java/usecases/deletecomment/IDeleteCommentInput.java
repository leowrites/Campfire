package usecases.deletecomment;

/** An interface that is intended to be implemented by the DeleteCommentInteractor class.
 * Holds one method, deleteComment, that must be implemented in all classes that implement
 * this interface.
 */
public interface IDeleteCommentInput {

    /** Creates a DeleteCommentResponseModel using the input from requestModel
     * @param requestModel a DeleteCommentRequestModel
     * @return a DeleteCommentResponseModel
     */
    DeleteCommentResponseModel deleteComment(DeleteCommentRequestModel requestModel);
}
