package service.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

/** The mapper for the Review data access object. Converts review data between object and Json,
 * used for SQL queries.
 */
public class ReviewDaoMapper implements RowMapper<Review> {

    /** Maps the row of the ResultSet to a Review object.
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result Review object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        String reviewData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(reviewData);
        Review review = gson.fromJson(object, Review.class);
        review.setId(rs.getObject("id", UUID.class));
        review.setInternshipId(rs.getObject("internshipid", UUID.class));
        review.setVotedUsers(new HashMap<>());
        return review;
    }
}
