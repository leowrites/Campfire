package user.deletecomment;

import entity.Review;
import exceptions.NotOwnCommentException;
import entity.Comment;
import exceptions.CommentNotFoundException;
import exceptions.NotEnoughAccessLevelException;
import service.dao.CommentDAO;
import service.dao.ReviewDAO;

public class DeleteCommentInteractor implements IDeleteCommentInput {

    private final ReviewDAO dataAccessReview;

    private final CommentDAO dataAccessComment;

    public DeleteCommentInteractor(ReviewDAO dataAccessReview, CommentDAO dataAccessComment) {
        this.dataAccessReview = dataAccessReview;
        this.dataAccessComment = dataAccessComment;
    }

    @Override
    public DeleteCommentResponseModel createResponseModel(DeleteCommentRequestModel requestModel) {
        int commentId = requestModel.getCommentId();
        String parentType = requestModel.getParentType();
        int parentId = requestModel.getParentId();
        String userId = requestModel.getUserId();
        int accessLevel = requestModel.getAccessLevel();

        // 1. delete this comment from the Comment table
        // commentDAO.delete(commentId)
        // 2. delete this comment from the parent
        // 3. differentiate between review and comment
        // if parentType == comment -> commentDAO.getComment <- get the parent comment object
        // comment.getComments()
        // comment will have a list of comments (int ids)
        // commentDAO.save(comment)
        // if not comment -> review -> reviewDAO.getReview(parentId)
        // review.getComments() (array list of int ids) - remove commentId
        // reviewDAO.save(review)

        Comment comment;


        // See if comment exists in DB
        try {
            comment = dataAccessComment.getComment(commentId);
            if (comment == null) {
                throw new CommentNotFoundException("Comment not found");
            }
        } catch (CommentNotFoundException e) {
            return new DeleteCommentResponseModel(e.getMessage(), dataAccessComment.getAllComments());
        }

        // See if user has access-level
        AccessLevelVerifier accessLevelVerifier = new AccessLevelVerifier(accessLevel);

        try {
            accessLevelVerifier.verify();
        } catch (NotEnoughAccessLevelException e) {
            return new DeleteCommentResponseModel(e.getMessage(), dataAccessComment.getAllComments());
        }


        // See if comment belongs to user
        OwnerVerifierComment ownerVerifierComment = new OwnerVerifierComment(comment, userId);

        try {
            ownerVerifierComment.verify();
        } catch (NotOwnCommentException e) {
            return new DeleteCommentResponseModel(e.getMessage(), dataAccessComment.getAllComments());
        }

        // 1. Delete this comment from the comment table
        dataAccessComment.deleteComment(commentId);

        // 2. Delete this comment from its parent

        if (parentType.equals("Review")) {
            Review parentReview = dataAccessReview.getReview(parentId);
            DeleteParentReview reviewHandler =
                    new DeleteParentReview(parentReview, commentId);
            Review newParentReview = reviewHandler.deleteComment();
            dataAccessReview.updateReview(newParentReview, parentId);
        } else {
            Comment parentComment = dataAccessComment.getComment(parentId);
            DeleteParentComment commentHandler =
                    new DeleteParentComment(parentComment, commentId);
            Comment newParentComment = commentHandler.deleteComment();
            dataAccessComment.updateComment(newParentComment, parentId);
        }

        // Return a success message, as well as updated Arraylist of comments
        return new DeleteCommentResponseModel("Comment has been successfully deleted",
                dataAccessComment.getAllComments());

    }
}
