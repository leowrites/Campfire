package user.delete_comment;

public class DeleteCommentController {

    final IDeleteCommentInput interactor;

    public DeleteCommentController(IDeleteCommentInput interactor){
        this.interactor = interactor;
    }

    public DeleteCommentResponseModel createDeleteCommentRequestModel(String parentType,
                                                                      String parentId,
                                                                      String commentId,
                                                                      String userId,
                                                                      int accessLevel){
        DeleteCommentRequestModel requestModel = new DeleteCommentRequestModel(parentType,
                parentId, commentId, userId, accessLevel);
        return interactor.createResponseModel(requestModel);
    }
}
