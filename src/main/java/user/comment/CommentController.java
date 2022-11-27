package user.comment;

// create a comment
public class CommentController {
    private final ICommentInputBoundary input;

    public CommentController(ICommentInputBoundary input){
        this.input = input;
    }

    public CommentResponseModel create(CommentRequestModel requestModel) {
        return input.create(requestModel);
    }
}
