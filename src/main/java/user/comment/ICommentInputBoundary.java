package user.comment;

public interface ICommentInputBoundary {
    CommentResponseModel create(CommentRequestModel requestModel);
}
