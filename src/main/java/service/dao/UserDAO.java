package service.dao;

import java.util.ArrayList;

// remove this dependency once db is implemented
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import usecases.requestconnect.exceptions.UserNotFoundException;

/** A data access object for the User database.
 */
@Repository
public class UserDAO implements IUserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO users (username, data) values (?, ?)";
    final String UPDATE_QUERY = "update users set data = ? where username = ?";
    final String DATA_QUERY = "select username, data from users where username = ? ";

    /** Query from the User database and return a User object given username.
     * @param username the username of the query
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

    /** Save a new User object as a json.
     * @param user the User object to be stored
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

    @Override
    public User save(User user) {
        return null;
    }

    /** Updates a User object.
     * @param user the new User object
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

    /** Resets the database of User objects and their connections and connection requests.
     */
    public void reset() {
    }
}
