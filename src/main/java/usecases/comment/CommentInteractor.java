package usecases.comment;

import entity.Comment;
import entity.IUserPost;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import usecases.comment.exceptions.ParentNotFoundException;
import service.ServerStatus;

import java.util.ArrayList;

/** The comment use case interactor that calls the create method from the ICommentInputBoundary
 * input boundary. When initialized, takes in an object that implements IReviewDAO to acces the
 * review database through, an object that implements ICommentDAO to access the comment
 * database through, and a CommentFactory in commentFactory.
 */

public class CommentInteractor implements ICommentInputBoundary {
    private final IReviewDAO reviewDAO;
    private final ICommentDAO commentDAO;
    private final CommentFactory commentFactory;

    public CommentInteractor(IReviewDAO reviewDAO, ICommentDAO commentDAO, CommentFactory commentFactory) {
        this.reviewDAO = reviewDAO;
        this.commentDAO = commentDAO;
        this.commentFactory = commentFactory;
    }

    /** Creates a comment as specified by the inputs in requestModel and updates the
     * corresponding database.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public CommentResponseModel create(CommentRequestModel requestModel) {
        String parentType = requestModel.getParentType();
        IParentOperationsStrategy strategy;
        try {
            strategy = ParentOperationsStrategyFactory.getStrategy(parentType, commentDAO, reviewDAO);
        }
        catch (ParentNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage(), -1, null);
        }

        int parentId = requestModel.getParentId();
        IUserPost parent;

        try {
            parent = strategy.getParent(parentId);
        }
        catch (ParentNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage(), -1, null);
        }

        String userId = requestModel.getUserId();
        String content = requestModel.getContent();
        Comment comment = commentFactory.createComment(userId, content);

        int commentId = commentDAO.saveComment(comment, parentId);
        ArrayList<Integer> parentComments = parent.getComments();
        parentComments.add(commentId);
        parent.setComments(parentComments);

        strategy.updateParent(parent, parentId);

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.", commentId, comment.getDatePosted());
    }
}

