package user.connect;

import java.io.*;
import java.util.Arrays;

// remove this dependency once db is implemented
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectionDataAccess implements IConnectionDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO users (username, data) values (?, ?)";
    final String UPDATE_QUERY = "update user set ? = ? where username = ?";
    final String DATA_QUERY = "select username, data from users where username = ? ";

    /**
     *
     * @param username username of the query
     * @return a User object
     */
    @Override
    public User getUser(String username) {
        return jdbcTemplate.queryForObject(DATA_QUERY, new UserDaoMapper());
    }

    /**
     * @param user saves a new connection
     */
    @Override
    public void saveUser(User user){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String userString = mapper.writeValueAsString(user);
            jdbcTemplate.update(INSERT_QUERY, user.getUsername(), userString);
        } catch(JsonProcessingException e){
            System.out.println("Json process error!");
        }
    }

    /**
     *
     * @param data new values to update, must be a stringified user object
     * @param username username
     */
    @Override
    public void updateUser(String data, String username) {
        jdbcTemplate.update(UPDATE_QUERY, data, username);
    }
}
