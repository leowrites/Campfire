package service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

public class CommentDAO implements ICommentDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO comments (data) values (?)";

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
}
