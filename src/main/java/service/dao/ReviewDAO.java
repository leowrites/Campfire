package service.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import user.comment.exceptions.ReviewNotFoundException;

public class ReviewDAO implements IReviewDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String INSERT_QUERY = "INSERT INTO reviews (data) values (?)";
    final String INSERT_QUERY_WITH_INTERNSHIP_ID = "INSERT INTO reviews (data, internshipid) values (?, ?)";
    final String QUERY_BY_INTERNSHIP_ID = "SELECT * FROM reviews WHERE internshipid = ?";
    final String DATA_QUERY = "select data from reviews where id = ? ";
    final String DATA_QUERY_WITH_ID = "select * from reviews where id = ? ";
    final String QUERY_ALL = "select * from reviews";
    final String UPDATE_QUERY = "update reviews set data = ? where id = ?";
    final String DELETE_QUERY = "delete from reviews where id = ?";

    /**
     * Gets the review given the review id
     * @param reviewId the id of the review
     * @return a review object
     */
    @Override
    public Review getReview(int reviewId){
        try {
            return jdbcTemplate.queryForObject(DATA_QUERY_WITH_ID, new ReviewDaoMapper(), reviewId);
        }
        catch (DataAccessException e) {
            System.out.println("No review found.");
            return null;
        }
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
    public int saveReview(Review review) {

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            // serialize the date to ISO-8601
            ObjectMapper mapper = new ObjectMapper();
            DaoHelper.formatDate(mapper);
            String reviewString = mapper.writeValueAsString(review);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, reviewString);
                return statement;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        } catch(JsonProcessingException e){
            System.out.println("Json process error!");
        }
        return 0;
    }

    @Override
    public int saveReview(Review review, int internshipid) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            // serialize the date to ISO-8601
            ObjectMapper mapper = new ObjectMapper();
            DaoHelper.formatDate(mapper);
            String reviewString = mapper.writeValueAsString(review);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY_WITH_INTERNSHIP_ID, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, reviewString);
                statement.setInt(2, internshipid);
                return statement;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        } catch(JsonProcessingException e){
            System.out.println("Json process error!");
        }
        return 0;
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
            ObjectMapper mapper = new ObjectMapper();
            DaoHelper.formatDate(mapper);
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

    @Override
    public ArrayList<Review> getReviewsByInternship(int internshipId) throws ReviewNotFoundException {
        try {
            return (ArrayList<Review>) jdbcTemplate.query(QUERY_BY_INTERNSHIP_ID, new ReviewDaoMapper(),
                    internshipId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No reviews under" + internshipId + " found");
            throw new ReviewNotFoundException("No reviews under internship" + internshipId + " found");
        }
    }
}
