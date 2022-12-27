package service.dao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Internship;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/** The mapper for the Internship data access object. Converts internship data between object and
 * Json, used for SQL queries.
 */
public class InternshipDaoMapper implements RowMapper<Internship> {

    /** Maps the row of the ResultSet to an Internship object.
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result Internship object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                       column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Internship mapRow(ResultSet rs, int rowNum) throws SQLException {
        String internshipData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(internshipData);
        return gson.fromJson(object, Internship.class);
    }
}
