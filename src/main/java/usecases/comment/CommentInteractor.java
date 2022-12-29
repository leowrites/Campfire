package usecases.comment;

import entity.Comment;
import entity.IUserPost;
import entity.User;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import usecases.comment.exceptions.ParentNotFoundException;
import service.ServerStatus;
import usecases.requestconnect.exceptions.UserNotFoundException;

import java.util.UUID;

/** The comment use case interactor that calls the create method from the ICommentInputBoundary
 * input boundary. When initialized, takes in an object that implements IReviewDAO to acces the
 * review database through, an object that implements ICommentDAO to access the comment
 * database through, and a CommentFactory in commentFactory.
 */

public class CommentInteractor implements ICommentInputBoundary {
    private final IReviewDAO reviewDAO;
    private final ICommentDAO commentDAO;
    private final CommentFactory commentFactory;

    private final IUserDAO userDAO;

    public CommentInteractor(IReviewDAO reviewDAO, ICommentDAO commentDAO, IUserDAO userDAO, CommentFactory commentFactory) {
        this.userDAO = userDAO;
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
        User user;
        String parentType = requestModel.getParentType();
        IParentOperationsStrategy strategy;
        try {
            strategy = ParentOperationsStrategyFactory.getStrategy(parentType, commentDAO, reviewDAO);
        }
        catch (ParentNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage(), null, null);
        }

        UUID parentId = requestModel.getParentId();
        IUserPost parent;

        try {
            parent = strategy.getParent(parentId);
        }
        catch (ParentNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage(), null, null);
        }

        try {
            user = userDAO.getUser(requestModel.getUsername());
        } catch(UserNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage(), null, null);
        }

        String content = requestModel.getContent();
        Comment comment = commentFactory.createComment(content);
        comment.setUser(user);
        comment.setParentId(parentId);

        Comment savedComment = commentDAO.save(comment, parentId);
        parent.getComments().add(savedComment);
        strategy.updateParent(parent);

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.", savedComment.getId(),
                comment.getDatePosted());
    }
}

