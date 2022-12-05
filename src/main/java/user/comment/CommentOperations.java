package user.comment;

import entity.Comment;
import entity.IUserPost;
import service.dao.ICommentDAO;
import user.comment.exceptions.ParentNotFoundException;

public class CommentOperations implements IParentOperationsStrategy {
    private final ICommentDAO commentDAO;

    public CommentOperations(ICommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public IUserPost getParent(int parentId) throws ParentNotFoundException {
        Comment comment = commentDAO.getComment(parentId);
        if (comment == null) {
            throw new ParentNotFoundException("Comment does not exist.");
        }
        return comment;
    }

    @Override
    public void updateParent(IUserPost parent, int parentId) {
        commentDAO.updateComment((Comment) parent, parentId);
    }

}