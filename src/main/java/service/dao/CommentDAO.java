package service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import entity.Comment;
import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TimeZone;

import service.dao.DaoHelper;

public class CommentDAO implements ICommentDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "insert into comments (data) values (?)";
    final String SELECT_QUERY = "select data from comments where id = ?";
    final String UPDATE_QUERY = "update comments set data = ? where id = ?";
    final String DELETE_QUERY = "delete from comments where id = ?";
    final String QUERY_ALL = "select * from comments";

    /**
     * Save a new comment as a json
     *
     * @param comment the comment object to be stored
     * @return an integer representing the id of the comment in the table
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

    /**
     * Gets the comment given the commentId.
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

    /**
     * Updates a comment.
     * @param comment   the new comment object
     * @param commentId the id of the comment to be updated
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

    /**
     * Deletes a comment.
     * @param commentId the id of the comment to be deleted
     */
    @Override
    public void deleteComment(int commentId) {
        try {
            jdbcTemplate.update(DELETE_QUERY, commentId);
        }
        catch (DataAccessException e) {
            System.out.println("No comment with that ID available to delete.");
        }
    }
}
