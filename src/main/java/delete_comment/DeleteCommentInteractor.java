package delete_comment;

import entity.Review;
import delete_comment.exceptions.CommentNotFoundException;
import delete_comment.exceptions.NotModeratorException;

import java.util.ArrayList;

public class DeleteCommentInteractor implements IDeleteCommentInput{

    @Override
    public DeleteCommentResponseModel createResponseModel(DeleteCommentRequestModel request_model){
        ArrayList<Review> comments = request_model.getcomments();
        String id = request_model.getid();
        int access_level = request_model.getaccesslevel();

        try {
            for (Review comment : comments) {
                if (comment.getid() == id){
                    break;
                }
                throw new CommentNotFoundException("Comment not found");
            }
        } catch (CommentNotFoundException e){
            return new DeleteCommentResponseModel(e.getMessage());
        }

        AccessLevelVerifier verifier = new AccessLevelVerifier(access_level);

        try {
            verifier.verify();
        } catch (NotModeratorException e){
            return new DeleteCommentResponseModel(e.getMessage());
        }

        DeleteCommentHandler handler = new DeleteCommentHandler(id, comments);

        handler.deletecomment(id, comments);

        return new DeleteCommentResponseModel("Comment has been successfully deleted");
    }

}
