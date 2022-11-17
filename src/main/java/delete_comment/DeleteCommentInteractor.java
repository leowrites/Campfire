package delete_comment;

import delete_comment.exceptions.NotOwnCommentException;
import entity.Comment;
import exceptions.CommentNotFoundException;
import exceptions.NotModeratorException;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DeleteCommentInteractor implements IDeleteCommentInput{

    private final IDeleteCommentDataAccess dataAccess;

    public DeleteCommentInteractor(IDeleteCommentDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public DeleteCommentResponseModel createResponseModel(DeleteCommentRequestModel requestModel){
        ArrayList<Comment> comments = requestModel.getComments(); // This variable will be removed after connected to DB
        String commentId = requestModel.getCommentId();
        String userId = requestModel.getUserId();
        int accessLevel = requestModel.getaccesslevel();
        Comment comment;


        // See if comment exists in DB
        /* pull from database here */
        try {
            comment = dataAccess.getComment(commentId);
            if (comment == null){
                throw new CommentNotFoundException("Comment not found");
            }
        } catch (CommentNotFoundException e){
            return new DeleteCommentResponseModel(e.getMessage());
        }


        // See if user is a moderator
        boolean isModerator = TRUE; //Boolean is not actually used, just to help understand code
        UserAccessLevelVerifier verifier = new UserAccessLevelVerifier(accessLevel);

        try {
            verifier.verify();
        } catch (NotModeratorException e){
            isModerator = FALSE;
            return new DeleteCommentResponseModel(e.getMessage());
        }


        // See if comment belongs to user
        boolean isOwnComment = TRUE; //Boolean is not actually used, just to help understand code
        UserOwnCommentVerifier verifier1 = new UserOwnCommentVerifier(comment, userId);

        try {
            verifier1.verify();
        } catch (NotOwnCommentException e){
            isOwnComment = FALSE;
            return new DeleteCommentResponseModel(e.getMessage());
        }


        //Need to change this to make it remove from database (Method shouldn't take in an ArrayList,
        //should go into database and remove the comment there

        DeleteCommentHandler handler = new DeleteCommentHandler(commentId, comments);

        handler.deleteComment(commentId, comments);

        return new DeleteCommentResponseModel("Comment has been successfully deleted");
    }

}
