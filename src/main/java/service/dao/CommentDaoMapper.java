package service.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Comment;
import org.postgresql.util.PSQLException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The mapper for the Comment data access object. Converts comment data between object and Json,
 * used for SQL queries.
 */
public class CommentDaoMapper implements RowMapper<Comment> {

    /** Maps the row of the ResultSet to a Comment object.
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result Comment object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        String commentData = rs.getString("data");
        Gson gson = new Gson();
        JsonObject object = (JsonObject) JsonParser.parseString(commentData);
        Comment comment = gson.fromJson(object, Comment.class);
        try {
            comment.setId(rs.getInt("id"));
            comment.setParentId(rs.getInt("parentId"));
        } catch (PSQLException e) {
            System.out.print("At CommentDaoMapper ");
            System.out.println(e);
        }
        return comment;
    }
}