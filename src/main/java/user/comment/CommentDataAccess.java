package user.comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import entity.Review;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.TimeZone;

@Repository
public class CommentDataAccess implements ICommentDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String COMMENT_INSERT_QUERY = "INSERT INTO comments (data) values (?)";
    final String REVIEW_UPDATE_QUERY = "update reviews set data = ? where id = ?";
    final String REVIEW_SELECT_QUERY = "select data from reviews where id = ?";


    @Override
    public Review getReview(String reviewId) {
        try {
            int id = Integer.parseInt(reviewId);
            return jdbcTemplate.queryForObject(REVIEW_SELECT_QUERY,  new ReviewDaoMapper(), id);
        } catch (DataAccessException e) {
            System.out.println("No review found.");
            return null;
        }
    }

    @Override
    public String saveComment(Comment comment){
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            ObjectMapper m = new ObjectMapper();
            String commentString = m.writeValueAsString(comment);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(COMMENT_INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, commentString);
                return statement;
            }, keyHolder);
            return Objects.requireNonNull(keyHolder.getKeys()).get("id").toString();
        }
        catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
            return null;
        }
    }

    @Override
    public void updateReview(Review review) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
            mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
            String reviewString = mapper.writeValueAsString(review);
            jdbcTemplate.update(REVIEW_UPDATE_QUERY, reviewString, Integer.parseInt(review.getId()));
        } catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
        }
    }
}
