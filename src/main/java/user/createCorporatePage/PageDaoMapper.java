package user.createCorporatePage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Page;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PageDaoMapper implements RowMapper<Page> {

    @Override
    public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
        String pageData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(pageData);
        return gson.fromJson(object, Page.class);
    }
}
