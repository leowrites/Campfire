package usecases.deletecomment;

import entity.Review;
import entity.Comment;
import entity.User;
import usecases.exceptions.CommentNotFoundException;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** The deletecomment use case interactor that calls the deleteComment method from the
 * IDeleteCommentInput input boundary. When initialized, takes in an object that implements
 * IReviewDAO to access the review database through, an object that implements ICommentDAO
 * to access the comment database through, and an object that implements IUserDAO to access
 * the user database through.
 */
public class DeleteCommentInteractor implements IDeleteCommentInput {
    private final IReviewDAO dataAccessReview;
    private final ICommentDAO dataAccessComment;
    private final IUserDAO userDAO;

    public DeleteCommentInteractor(IReviewDAO dataAccessReview, ICommentDAO dataAccessComment, IUserDAO userDAO) {
        this.dataAccessReview = dataAccessReview;
        this.dataAccessComment = dataAccessComment;
        this.userDAO = userDAO;
    }

    /** Deletes the comment as specified by the inputs in requestModel from the comment database.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public DeleteCommentResponseModel deleteComment(DeleteCommentRequestModel requestModel) {
        int commentId = requestModel.getCommentId();
        int parentId = requestModel.getParentId();
        String parentType = requestModel.getParentType();
        String userId = requestModel.getUserId();
        Comment comment;
        User user;

        // See if comment exists in DB
        try {
            comment = dataAccessComment.getComment(commentId);
            user = userDAO.getUser(userId);
            if (comment == null) {
                throw new CommentNotFoundException("Comment not found");
            }
        } catch (CommentNotFoundException | UserNotFoundException e) {
            return new DeleteCommentResponseModel(e.getMessage());
        }

        if (user.getAccessLevel() == 0 && !userId.equals(comment.getUserId())){
            return new DeleteCommentResponseModel("Not authorized!");
        }

        dataAccessComment.deleteComment(commentId);

        if (parentType.equals("Review")) {
            Review parentReview = dataAccessReview.getReview(parentId);
            List<Integer> filteredComments = parentReview.getComments()
                    .stream()
                    .filter(id -> id != commentId)
                    .collect(Collectors.toList());
            parentReview.setComments((ArrayList<Integer>) filteredComments);
            dataAccessReview.updateReview(parentReview, parentId);
        } else {
            Comment parentComment = dataAccessComment.getComment(parentId);
            List<Integer> filteredComments = parentComment.getComments()
                    .stream()
                    .filter(id -> id != commentId)
                    .collect(Collectors.toList());
            parentComment.setComments((ArrayList<Integer>) filteredComments);
            dataAccessComment.updateComment(parentComment, parentId);
        }

        // Return a success message, as well as updated Arraylist of comments
        return new DeleteCommentResponseModel("Comment has been successfully deleted");
    }
}
