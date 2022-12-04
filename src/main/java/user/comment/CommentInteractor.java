package user.comment;

import entity.Comment;
import entity.IUserPost;
import entity.Review;
import user.comment.exceptions.CommentNotFoundException;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import user.comment.exceptions.ReviewNotFoundException;
import service.ServerStatus;

import java.util.ArrayList;

/** The comment use case interactor that calls the create method from the ICommentInputBoundary
 * input boundary. When initialized, takes in an object that implements IReviewDAO to acces the
 * review database through, and an object that implements ICommentDAO to access the comment
 * database through.
 */
public class CommentInteractor extends CommentObservable implements ICommentInputBoundary {
    private final IReviewDAO reviewDAO;
    private final ICommentDAO commentDAO;

    public CommentInteractor(IReviewDAO reviewDAO, ICommentDAO commentDAO) {
        this.reviewDAO = reviewDAO;
        this.commentDAO = commentDAO;
    }

    /** Creates a comment as specified by the inputs in requestModel and updates the
     * corresponding database.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public CommentResponseModel create(CommentRequestModel requestModel) {
        String userId = requestModel.getUserId();
        String parentType = requestModel.getParentType();
        int parentId = requestModel.getParentId();
        IUserPost parent;
        String content = requestModel.getContent();
        Comment comment = new Comment(userId, content);

        if (parentType.equals("Review")) {
            try {
                parent = reviewDAO.getReview(parentId);
                if (parent == null) {
                    throw new ReviewNotFoundException("Review does not exist.");
                }
            }
            catch (ReviewNotFoundException e) {
                return new CommentResponseModel(ServerStatus.ERROR, e.getMessage());
            }
        }
        else {
            try {
                parent = commentDAO.getComment(parentId);
                if (parent == null) {
                    throw new CommentNotFoundException("Comment does not exist.");
                }
            }
            catch (CommentNotFoundException e) {
                return new CommentResponseModel(ServerStatus.ERROR, e.getMessage());
            }
        }

        int commentId = commentDAO.saveComment(comment);
        ArrayList<Integer> parentComments = parent.getComments();
        parentComments.add(commentId);
        parent.setComments(parentComments);

        if (parentType.equals("Review")) {
            reviewDAO.updateReview((Review) parent, parentId);
        }
        else {
            commentDAO.updateComment((Comment) parent, parentId);
        }

        
        // notify observers that a new comment has been made

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.");
    }
}

