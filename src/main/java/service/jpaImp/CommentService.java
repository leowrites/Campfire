package service.jpaImp;

import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import service.dao.ICommentDAO;
import usecases.exceptions.CommentNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class CommentService implements ICommentDAO {
    @Autowired
    CommentRepository commentRepository;


    /**
     * Save a new Comment object.
     *
     * @param comment the Comment object to be stored
     * @return an int representing the id of the Comment object
     */
    @Override
    public int saveComment(Comment comment) {
        return 0;
    }

    /**
     * Save a new Comment object with its parent's id.
     *
     * @param comment  the Comment object to be stored
     * @param parentId the id of the parent object of the comment
     * @return an int representing the id of the comment
     */
    @Override
    public int saveComment(Comment comment, int parentId) {
        return 0;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment save(Comment comment, UUID parentId) {
        return commentRepository.save(comment);
    }

    /**
     * Gets the Comment object given by the commentId.
     *
     * @param commentId the id of the comment
     * @return a Comment object
     */
    @Override
    public Comment getComment(int commentId) {
        return null;
    }

    @Override
    public Comment getComment(UUID commentId) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            return comment.get();
        } else {
            throw new CommentNotFoundException("No Comment Found!");
        }
    }

    /**
     * Gets all the comments under a parent given its parentId.
     *
     * @param parentId the id of the parent
     * @return an ArrayList of Comment objects under the parent
     */
    @Override
    public ArrayList<Comment> getCommentsWithParentId(int parentId) {
        return null;
    }

    @Override
    public List<Comment> getCommentsWithParentId(UUID parentId) {
        return null;
    }

    /**
     * Updates a Comment object.
     *
     * @param comment   the new Comment object
     * @param commentId the id of the Comment object to be updated
     */
    @Override
    public void updateComment(Comment comment, int commentId) {

    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Deletes a Comment object.
     *
     * @param commentId the id of the Comment object to be deleted
     */
    @Override
    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }
}
