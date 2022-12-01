package service.dao;

import entity.Comment;

import java.util.ArrayList;

public interface ICommentDAO {
    /**
     * Save a new comment as a json
     * @param comment the comment object to be stored
     * @return an integer representing the id of the comment in the table
     */
    int saveComment(Comment comment);

    /**
     * Gets the comment given the commentId.
     * @param commentId the id of the comment
     * @return a Comment object
     */
    Comment getComment(int commentId);

    /**
     * Updates a comment.
     * @param comment   the new comment object
     * @param commentId the id of the comment to be updated
     */
    void updateComment(Comment comment, int commentId);

    /**
     * Deletes a comment.
     * @param commentId the id of the comment to be deleted
     */
    void deleteComment(int commentId);

    /**
     * @return all comments
     */
    public ArrayList<Comment> getAllComments();
}
