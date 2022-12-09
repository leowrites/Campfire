package service.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The mapper for the User data access object. Converts user data between object and Json,
 * used for SQL queries.
 */
public class UserDaoMapper implements RowMapper<User> {

    /** Maps the row of the ResultSet to a User object.
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result User object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(userData);
        System.out.println(object);
        User user = gson.fromJson(object, User.class);
        System.out.println(user);
        return gson.fromJson(object, User.class);
    }
}
