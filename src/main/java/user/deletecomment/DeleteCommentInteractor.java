package user.deletecomment;

import entity.Review;
import exceptions.NotOwnCommentException;
import entity.Comment;
import exceptions.CommentNotFoundException;
import exceptions.NotEnoughAccessLevelException;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteCommentInteractor implements IDeleteCommentInput {

    private final IReviewDAO dataAccessReview;

    private final ICommentDAO dataAccessComment;

    public DeleteCommentInteractor(IReviewDAO dataAccessReview, ICommentDAO dataAccessComment) {
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
        Comment comment;


        // See if comment exists in DB
        try {
            comment = dataAccessComment.getComment(commentId);
            if (comment == null) {
                throw new CommentNotFoundException("Comment not found");
            }
        } catch (CommentNotFoundException e) {
            return new DeleteCommentResponseModel(e.getMessage(), null);
        }
//
//        // See if user has access-level
//        AccessLevelVerifier accessLevelVerifier = new AccessLevelVerifier(accessLevel);
//
//        try {
//            accessLevelVerifier.verify();
//        } catch (NotEnoughAccessLevelException e) {
//            return new DeleteCommentResponseModel(e.getMessage(), null);
//        }

        // See if comment belongs to user
//        OwnerVerifierComment ownerVerifierComment = new OwnerVerifierComment(comment, userId);
//
//        try {
//            ownerVerifierComment.verify();
//        } catch (NotOwnCommentException e) {
//            return new DeleteCommentResponseModel(e.getMessage(), dataAccessComment.getAllComments());
//        }
//
        if (accessLevel == 0 && !userId.equals(comment.getUserId())){
            return new DeleteCommentResponseModel("Not authorized!", null);
        }

        dataAccessComment.deleteComment(commentId);

        if (parentType.equals("Review")) {
            Review parentReview = dataAccessReview.getReview(parentId);

            List<Integer> filteredComments = parentReview.getComments()
                    .stream()
                    .filter(id -> id != commentId)
                    .collect(Collectors.toList());

//            DeleteParentReview reviewHandler =
//                    new DeleteParentReview(parentReview, commentId);
//            Review newParentReview = reviewHandler.deleteComment();
            parentReview.setComments((ArrayList<Integer>) filteredComments);
            dataAccessReview.updateReview(parentReview, parentId);
        } else {
            Comment parentComment = dataAccessComment.getComment(parentId);
//            DeleteParentComment commentHandler =
//                    new DeleteParentComment(parentComment, commentId);
//            Comment newParentComment = commentHandler.deleteComment();
            List<Integer> filteredComments = parentComment.getComments()
                    .stream()
                    .filter(id -> id != commentId)
                    .collect(Collectors.toList());
            dataAccessComment.updateComment(parentComment, parentId);
        }

        // Return a success message, as well as updated Arraylist of comments
        return new DeleteCommentResponseModel("Comment has been successfully deleted", null);
    }
}
