package user.requestconnect;

import java.io.*;

// remove this dependency once db is implemented
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.acceptconnect.UserDaoMapper;
import user.requestconnect.exceptions.UserNotFoundException;

@Repository
public class RequestConnectionDataAccess implements IRequestConnectionDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO users (username, data) values (?, ?)";
    final String UPDATE_QUERY = "update user set ? = ? where username = ?";
    final String DATA_QUERY = "select username, data from users where username = ? ";

    /**
     * query from db and return a user object given username
     * @param username username of the query
     * @return a User object
     */
    @Override
    public User getUser(String username) throws UserNotFoundException {
        try {
            return jdbcTemplate.queryForObject(DATA_QUERY,  new UserDaoMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No user found!");
            throw new UserNotFoundException("No user found!");
        }
    }

    /**
     * save a new user object to db
     * @param user a user object
     */
    @Override
    public void saveUser(User user){
        try{
            ObjectMapper mapper = new ObjectMapper();
            // need to verify username is not duplicated
            String userString = mapper.writeValueAsString(user);
            jdbcTemplate.update(INSERT_QUERY, user.getUsername(), userString);
        } catch(JsonProcessingException e){
            System.out.println("Json process error!");
        }
    }

    /**
     * updates a user given a user object
     * @param user a user object
     */
    @Override
    public void updateUser(User user) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String userString = mapper.writeValueAsString(user);
            jdbcTemplate.update(UPDATE_QUERY, userString, user.getUsername());
        } catch(JsonProcessingException e){
            System.out.println("Json process error!");
        }
    }
}
