package usecases.deletecomment;

/** A response model for the deletecomment use case that frames the output data into an object.
 * Holds the String message about the status of the deletion of the comment.
 */
public class DeleteCommentResponseModel {

    private final String message;

    public DeleteCommentResponseModel(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
