package user.comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Review;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDataAccess implements ICommentDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String COMMENT_INSERT_QUERY = "INSERT INTO comments (data) values (?)";
    final String REVIEW_UPDATE_QUERY = "update reviews set data = ? where id = ?";
    final String REVIEW_SELECT_QUERY = "select id, data from reviews where id = ?";

    @Override
    public Review getReview(String reviewID) {
        try {
            return jdbcTemplate.queryForObject(REVIEW_SELECT_QUERY,  new ReviewDaoMapper(), reviewID);
        } catch (DataAccessException e) {
            System.out.println("No review found.");
            return null;
        }
    }

    @Override
    public void insertComment(Comment comment){
        try {
            ObjectMapper m = new ObjectMapper();
            String commentString = m.writeValueAsString(comment);
            jdbcTemplate.update(COMMENT_INSERT_QUERY, commentString);
        } catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
        }
    }

    @Override
    public void updateReview(Review review) {
        try {
            ObjectMapper m = new ObjectMapper();
            String reviewString = m.writeValueAsString(review);
            jdbcTemplate.update(REVIEW_UPDATE_QUERY, reviewString, review.getID());
        } catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
        }
    }
}
