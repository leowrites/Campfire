package delete_comment;

import entity.Comment;
import entity.Review;

import java.util.ArrayList;

public class DeleteCommentController {

    final IDeleteCommentInput interactor;

    public DeleteCommentController(IDeleteCommentInput interactor){
        this.interactor = interactor;
    }

    public DeleteCommentResponseModel createDeleteCommentrequestmodel(ArrayList<Comment> comments,
                                                                      String commentId,
                                                                      String userId,
                                                                      int accessLevel){
        DeleteCommentRequestModel requestModel = new DeleteCommentRequestModel(comments, commentId, userId, accessLevel);
        return interactor.createResponseModel(requestModel);
    }
}
