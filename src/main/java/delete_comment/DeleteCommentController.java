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
                                                                      String id,
                                                                      int access_level){
        DeleteCommentRequestModel requestModel = new DeleteCommentRequestModel(comments, id, access_level);
        return interactor.createResponseModel(requestModel);
    }
}
