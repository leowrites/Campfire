package service.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Review;
import org.postgresql.util.PSQLException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDaoMapper implements RowMapper<Review> {
    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call {@code next()} on
     * the ResultSet; it is only supposed to map values of the current row.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        String reviewData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(reviewData);
        Review review = gson.fromJson(object, Review.class);
        try {
            rs.getInt("internshipid");
            review.setInternshipId(rs.getInt("internshipid"));
        } catch (PSQLException e) {
            System.out.println(e);
        }
        return review;
    }
}
