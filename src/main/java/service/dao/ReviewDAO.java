package service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
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

public class ReviewDAO implements IReviewDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String INSERT_QUERY = "INSERT INTO reviews (data) values (?)";
    final String DATA_QUERY = "select data from reviews where id = ? ";
    final String QUERY_ALL = "select * from reviews";
    final String UPDATE_QUERY = "update reviews set data = ? where id = ?";
    final String DELETE_QUERY = "delete from reviews where id = ?";

    /**
     * Gets the review given the review id
     * @param reviewId the id of the review
     * @return a review object
     */
    @Override
    public Review getReview(String reviewId){
        return jdbcTemplate.queryForObject(DATA_QUERY, new ReviewDaoMapper(), Integer.parseInt(reviewId));
    }

    /**
     * @return all reviews
     */
    @Override
    public ArrayList<Review> getAllReviews() {
        return (ArrayList<Review>) jdbcTemplate.query(QUERY_ALL, new ReviewDaoMapper());
    }

    /**
     * Creates a new review in the db given a review object
     * @param review the review to be saved
     * @return the id of the created review
     */
    @Override
    public String saveReview(Review review) {

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            // serialize the date to ISO-8601

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
            mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
            String reviewString = mapper.writeValueAsString(review);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, reviewString);
                return statement;
            }, keyHolder);
            return Objects.requireNonNull(keyHolder.getKeys()).get("id").toString();
        } catch(JsonProcessingException e){
            System.out.println("Json process error!");
        }
        return null;
    }

    /**
     * Updates a review
     *
     * @param review   the new review object
     * @param reviewId the id of the review
     */
    @Override
    public void updateReview(Review review, int reviewId) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
            mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
            String reviewString = mapper.writeValueAsString(review);
            jdbcTemplate.update(UPDATE_QUERY, reviewString, reviewId);
        } catch (JsonProcessingException e) {
            System.out.println("There was an error in the JSON processing.");
        }
    }

    /**
     * Deletes a review.
     * @param reviewId the id of the comment to be deleted
     */
    @Override
    public void deleteReview(int reviewId) {
        try {
            jdbcTemplate.update(DELETE_QUERY, reviewId);
        }
        catch (DataAccessException e) {
            System.out.println("No review with that ID available to delete.");
        }
    }
}
