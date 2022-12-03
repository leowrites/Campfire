package service.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDaoMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        String commentData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(commentData);
        return gson.fromJson(object, Comment.class);
    }
}