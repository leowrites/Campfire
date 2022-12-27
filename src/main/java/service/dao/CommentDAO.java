package service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import usecases.exceptions.CommentNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** A data access object for the Comment database.
 */
public class CommentDAO implements ICommentDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "insert into comments (data) values (?)";
    final String INSERT_QUERY_WITH_ID = "insert into comments (data, parentid) values (?, ?)";
    final String SELECT_QUERY = "select * from comments where id = ?";
    final String SELECT_QUERY_WITH_PARENT_ID = "select * from comments where parentid = ?";
    final String UPDATE_QUERY = "update comments set data = ? where id = ?";
    final String DELETE_QUERY = "delete from comments where id = ?";

    /** Save a new Comment object as a json.
     * @param comment the Comment object to be stored
     * @return an int representing the id of the Comment object in the table
     */
    @Override
    public int saveComment(Comment comment) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            ObjectMapper m = new ObjectMapper();
            DaoHelper.formatDate(m);
            String commentString = m.writeValueAsString(comment);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, commentString);
                return statement;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        }
        catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
            return 0;
        }
    }

    /** Save a new Comment object as a json with its parent's id.
     * @param comment the Comment object to be stored
     * @param parentId the id of the parent object of the comment
     * @return an int representing the id of the comment in the table
     */
    @Override
    public int saveComment(Comment comment, int parentId) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            ObjectMapper m = new ObjectMapper();
            DaoHelper.formatDate(m);
            String commentString = m.writeValueAsString(comment);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY_WITH_ID, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, commentString);
                statement.setInt(2, parentId);
                return statement;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        }
        catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
            return 0;
        }
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Comment save(Comment comment, UUID parentId) {
        return null;
    }

    /** Gets the Comment object given by the commentId.
     * @param commentId the id of the comment
     * @return a Comment object
     */
    @Override
    public Comment getComment(int commentId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_QUERY, new CommentDaoMapper(), commentId);
        }
        catch (DataAccessException e) {
            System.out.println("No comment found.");
            return null;
        }
    }

    @Override
    public Comment getComment(UUID commentId) throws CommentNotFoundException {
        return null;
    }

    /** Gets all the comments under a parent given its parentId.
     * @param parentId the id of the parent
     * @return an ArrayList of Comment objects under the parent
     */
    @Override
    public ArrayList<Comment> getCommentsWithParentId(int parentId) {
            try {
                return (ArrayList<Comment>) jdbcTemplate.query(SELECT_QUERY_WITH_PARENT_ID, new CommentDaoMapper(),
                        parentId);
            } catch (EmptyResultDataAccessException e) {
                System.out.println("No Internships Under" + parentId + " found");
            }
            return  null;
    }

    @Override
    public List<Comment> getCommentsWithParentId(UUID parentId) {
        return null;
    }

    /** Updates a Comment object.
     * @param comment the new Comment object
     * @param commentId the id of the Comment object to be updated
     */
    @Override
    public void updateComment(Comment comment, int commentId) {
        try {
            ObjectMapper m = new ObjectMapper();
            DaoHelper.formatDate(m);
            String commentString = m.writeValueAsString(comment);
            jdbcTemplate.update(UPDATE_QUERY, commentString, commentId);
        }
        catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
        }
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    /** Deletes a Comment object.
     * @param commentId the id of the Comment object to be deleted
     */
    @Override
    public void deleteComment(UUID commentId) {
        try {
            jdbcTemplate.update(DELETE_QUERY, commentId);
        }
        catch (DataAccessException e) {
            System.out.println("No comment with that ID available to delete.");
        }
    }
}
