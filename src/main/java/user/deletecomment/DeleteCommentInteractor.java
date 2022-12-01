package user.deletecomment;

import exceptions.NotOwnCommentException;
import entity.Comment;
import exceptions.CommentNotFoundException;
import exceptions.NotEnoughAccessLevelException;
import service.dao.CommentDAO;
import service.dao.ReviewDAO;

import java.util.ArrayList;

public class DeleteCommentInteractor implements IDeleteCommentInput{

    private final CommentDAO dataAccessComment;

    public DeleteCommentInteractor(ReviewDAO dataAccessReview, CommentDAO dataAccessComment) {
        this.dataAccessReview = dataAccessReview;
        this.dataAccessComment = dataAccessComment;
    }

    @Override
    public DeleteCommentResponseModel createResponseModel(DeleteCommentRequestModel requestModel){
        String commentId = requestModel.getCommentId();
        String parentType = requestModel.getParentType();
        String parentId = requestModel.getParentId();
        String userId = requestModel.getUserId();
        int accessLevel = requestModel.getAccessLevel();

        ArrayList<Comment> comments = dataAccess.getComments(parentType, parentId);
        ArrayList<Comment> commentsNew;
        Comment comment;


        // See if comment exists in DB
        try {
            comment = dataAccessComment.getComment(commentId);
            if (comment == null){
                throw new CommentNotFoundException("Comment not found");
            }
        } catch (CommentNotFoundException e){
            return new DeleteCommentResponseModel(e.getMessage(), comments);
        }


        // See if user has access-level
        AccessLevelVerifier accessLevelVerifier = new AccessLevelVerifier(accessLevel);

        try {
            accessLevelVerifier.verify();
        } catch (NotEnoughAccessLevelException e){
            return new DeleteCommentResponseModel(e.getMessage(), comments);
        }


        // See if comment belongs to user
        OwnerVerifierComment ownerVerifierComment = new OwnerVerifierComment(comment, userId);

        try {
            ownerVerifierComment.verify();
        } catch (NotOwnCommentException e){
            return new DeleteCommentResponseModel(e.getMessage(), comments);
        }


        // Using parentId, get the list of corresponding comments
        // Handler will delete the specific comment from the ArrayList, will update the parent
        // with updated list of comments.

        DeleteCommentHandler handler = new DeleteCommentHandler(commentId, comments);

        commentsNew = handler.deleteComment();

        // Update the database with new Arraylist of comments
        dataAccess.updateComments(parentType, parentId, commentsNew);

        // Return a success message, as well as updated Arraylist of comments
        return new DeleteCommentResponseModel("Comment has been successfully deleted", commentsNew);
    }

}
