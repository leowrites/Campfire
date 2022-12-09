package service.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Corporate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The mapper for the Corporate data access object. Converts corporate data between object and Json,
 * used for SQL queries.
 */
public class CorporateDaoMapper implements RowMapper<Corporate> {

    /** Maps the row of the ResultSet to a Corporate object.
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result Corporate object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Corporate mapRow(ResultSet rs, int rowNum) throws SQLException {
        String corporateData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(corporateData);
        Corporate corporate = gson.fromJson(object, Corporate.class);
        corporate.setId(rs.getInt("id"));
        return corporate;
    }
}
