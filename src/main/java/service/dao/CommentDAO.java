package service.dao;

import entity.Comment;
public class CommentDAO implements ICommentDAO{
    /**
     * Save a new comment as a json
     *
     * @param comment the comment object to be stored
     * @return an integer representing the id of the comment in the table
     */
    @Override
    public int saveComment(Comment comment) {
        return 0;
    }
}
