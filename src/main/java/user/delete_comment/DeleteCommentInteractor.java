package user.delete_comment;

import user.delete_comment.exceptions.NotOwnCommentException;
import entity.Comment;
import exceptions.CommentNotFoundException;
import exceptions.NotModeratorException;

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
        String parentId = requestModel.getParentId();
        String commentId = requestModel.getCommentId();
        String userId = requestModel.getUserId();
        int accessLevel = requestModel.getaccesslevel();
        ArrayList<Comment> comments = dataAccess.getComments(parentId);
        ArrayList<Comment> commentsNew;
        Comment comment;


        // See if comment exists in DB
        try {
            comment = dataAccess.getComment(commentId);
            if (comment == null){
                throw new CommentNotFoundException("Comment not found");
            }
        } catch (CommentNotFoundException e){
            return new DeleteCommentResponseModel(e.getMessage(), comments);
        }


        // See if user is a moderator
        ModeratorVerifier moderatorVerifier = new ModeratorVerifier(accessLevel);

        try {
            moderatorVerifier.verify();
        } catch (NotModeratorException e){
            return new DeleteCommentResponseModel(e.getMessage(), comments);
        }


        // See if comment belongs to user
        OwnerVerifier ownerVerifier = new OwnerVerifier(comment, userId);

        try {
            ownerVerifier.verify();
        } catch (NotOwnCommentException e){
            return new DeleteCommentResponseModel(e.getMessage(), comments);
        }


        // Using parentId, get the list of corresponding comments
        // Handler will delete the specific comment from the ArrayList, will update the parent
        // with updated list of comments.

        DeleteCommentHandler handler = new DeleteCommentHandler(commentId, comments);

        commentsNew = handler.deleteComment(commentId, comments);

        // Update the database with new Arraylist of comments
        dataAccess.updateComments(parentId, commentsNew);

        // Return a success message, as well as updated Arraylist of comments
        return new DeleteCommentResponseModel("Comment has been successfully deleted", commentsNew);
    }

}
