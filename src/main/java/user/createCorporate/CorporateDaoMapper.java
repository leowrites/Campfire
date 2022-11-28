package user.createCorporate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Corporate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// converts data between object and Json, used for SQL queries
public class CorporateDaoMapper implements RowMapper<Corporate> {

    @Override
    public Corporate mapRow(ResultSet rs, int rowNum) throws SQLException {
        String corporateData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(corporateData);
        return gson.fromJson(object, Corporate.class);
    }
}
