package service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Internship;

import entity.User;
import org.springframework.jdbc.core.RowMapper;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class InternshipRowMapper implements RowMapper<Internship> {

    @Override
    public Internship mapRow(ResultSet rs, int rowNum) throws SQLException {
        String internshipData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(internshipData);
        System.out.println(object);
        User user = gson.fromJson(object, User.class);
        System.out.println(user);
        Internship internship = gson.fromJson(object, Internship.class);
        internship.setCompanyID(rs.getInt("companyid"));
        return internship;
    }
}
