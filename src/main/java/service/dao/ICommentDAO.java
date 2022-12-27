package service.dao;
import entity.Comment;
import usecases.exceptions.CommentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** An interface for the Comment data access object.
 */
public interface ICommentDAO {

    /** Save a new Comment object.
     * @param comment the Comment object to be stored
     * @return an int representing the id of the Comment object
     */
    int saveComment(Comment comment);

    /** Save a new Comment object with its parent's id.
     * @param comment the Comment object to be stored
     * @param parentId the id of the parent object of the comment
     * @return an int representing the id of the comment
     */
    int saveComment(Comment comment, int parentId);

    Comment save(Comment comment);

    Comment save(Comment comment, UUID parentId);

    /** Gets the Comment object given by the commentId.
     * @param commentId the id of the comment
     * @return a Comment object
     */
    Comment getComment(int commentId);

    Comment getComment(UUID commentId) throws CommentNotFoundException;

    /** Gets all the comments under a parent given its parentId.
     * @param parentId the id of the parent
     * @return an ArrayList of Comment objects under the parent
     */
    ArrayList<Comment> getCommentsWithParentId(int parentId);
    List<Comment> getCommentsWithParentId(UUID parentId);

    /** Updates a Comment object.
     * @param comment the new Comment object
     * @param commentId the id of the Comment object to be updated
     */
    void updateComment(Comment comment, int commentId);

    Comment update(Comment comment);

    /** Deletes a Comment object.
     * @param commentId the id of the Comment object to be deleted
     */
    void deleteComment(UUID commentId);
}
