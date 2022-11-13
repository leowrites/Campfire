package delete_comment;

import entity.Review;
import user.connect.ConnectionRequestModel;

import java.util.ArrayList;

public class DeleteCommentController {

    final IDeleteCommentInput interactor;

    public DeleteCommentController(IDeleteCommentInput interactor){
        this.interactor = interactor;
    }

    public DeleteCommentResponseModel createDeleteCommentrequestmodel(ArrayList<Review> comments,
                                                                      String id,
                                                                      int access_level){
        DeleteCommentRequestModel requestModel = new DeleteCommentRequestModel(comments, id, access_level);
        return interactor.createResponseModel(requestModel);
    }
}
