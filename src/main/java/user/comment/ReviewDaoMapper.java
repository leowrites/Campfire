package user.comment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDaoMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(userData);
        return gson.fromJson(object, Review.class);
    }
}