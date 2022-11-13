package user.comment;

public class CommentController {
    private final CommentInputBoundary input;

    public CommentController(CommentInputBoundary input){
        this.input = input;
    }

    public CommentResponseModel create(CommentRequestModel requestModel) {
        return input.create(requestModel);
    }
}
