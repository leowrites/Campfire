package service.dao;
import entity.Comment;

import java.util.ArrayList;

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

    /** Gets the Comment object given by the commentId.
     * @param commentId the id of the comment
     * @return a Comment object
     */
    Comment getComment(int commentId);

    /** Gets all the comments under a parent given its parentId.
     * @param parentId the id of the parent
     * @return an ArrayList of Comment objects under the parent
     */
    ArrayList<Comment> getCommentsWithParentId(int parentId);

    /** Updates a Comment object.
     * @param comment the new Comment object
     * @param commentId the id of the Comment object to be updated
     */
    void updateComment(Comment comment, int commentId);

    /** Deletes a Comment object.
     * @param commentId the id of the Comment object to be deleted
     */
    void deleteComment(int commentId);
}
