package usecases.comment;

import entity.Comment;
import entity.IUserPost;
import service.dao.ICommentDAO;
import usecases.comment.exceptions.ParentNotFoundException;
import usecases.exceptions.CommentNotFoundException;

import java.util.UUID;

/** One of the operations strategy classes in the comment use case, for Comments. Takes in an
 * object that implements ICommentDAO to access the comment database through.
 */
public class CommentOperations implements IParentOperationsStrategy {
    private final ICommentDAO commentDAO;

    public CommentOperations(ICommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    /** Gets the parent object with the given id parentId.
     * @param parentId the parent's id
     * @return an IUserPost-implementing object
     * @throws ParentNotFoundException thrown when the parent with id parentId cannot be found
     */
    @Override
    public IUserPost getParent(UUID parentId) throws ParentNotFoundException {
        try {
            return commentDAO.getComment(parentId);
        } catch (CommentNotFoundException e) {
            throw new ParentNotFoundException("Comment does not exist.");
        }
    }

    /** Updates the corresponding database with the parent object and its id.
     * @param parent the parent object
     * @param parentId the parent's id
     */
    @Override
    public void updateParent(IUserPost parent, int parentId) {
        commentDAO.updateComment((Comment) parent, parentId);
    }

    @Override
    public void updateParent(IUserPost parent) {
        commentDAO.save((Comment) parent);
    }

}