package user.comment;

import entity.Comment;
import entity.IUserPost;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import user.comment.exceptions.ParentNotFoundException;
import service.ServerStatus;

import java.util.ArrayList;

public class CommentInteractor extends CommentObservable implements ICommentInputBoundary {
    private final IReviewDAO reviewDAO;
    private final ICommentDAO commentDAO;
    private final CommentFactory commentFactory;

    public CommentInteractor(IReviewDAO reviewDAO, ICommentDAO commentDAO, CommentFactory commentFactory) {
        this.reviewDAO = reviewDAO;
        this.commentDAO = commentDAO;
        this.commentFactory = commentFactory;
    }

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

        // notify observers that a new comment has been made

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.", commentId, comment.getDatePosted());
    }
}

