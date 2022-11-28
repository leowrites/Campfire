package service;

import java.util.ArrayList;

// remove this dependency once db is implemented
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.requestconnect.exceptions.UserNotFoundException;

@Repository
public class UserDataAccess implements IUserDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO users (username, data) values (?, ?)";
    final String UPDATE_QUERY = "update users set data = ? where username = ?";
    final String DATA_QUERY = "select username, data from users where username = ? ";
    final String QUERY_ALL = "select * from users";

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
     * @return all users
     */
    @Override
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) jdbcTemplate.query(QUERY_ALL, new UserDaoMapper());
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

    public void reset() {
//        ArrayList<String> user1incoming = new ArrayList<String>();
//        ArrayList<String> user1outgoing = new ArrayList<String>();
//        ArrayList<String> user1Connections = new ArrayList<String>();
//        ArrayList<String> user2incoming = new ArrayList<String>();
//        ArrayList<String> user2outgoing = new ArrayList<String>();
//        ArrayList<String> user2Connections = new ArrayList<String>();
//        User user1 = new User("01", user1incoming, user1Connections, user1outgoing,
//                "leoliu", "leo@gmail.com", "pass", "Leo");
//        User user2 = new User("02", user2incoming, user2Connections, user2outgoing,
//                "alex", "alex@gmail.com", "pass", "Alex");
//        User user3 = new User("01", user1incoming, user1Connections, user1outgoing,
//                "stevejobs", "steve@gmail.com", "pass", "steve");
//        User user4 = new User("02", user2incoming, user2Connections, user2outgoing,
//                "timcook", "tim@gmail.com", "pass", "tim");
//        saveUser(user1);
//        saveUser(user2);
//        saveUser(user3);
//        saveUser(user4);
        ArrayList<User> users = getUsers();
        for (User user : users){
            user.setConnections(new ArrayList<>());
            user.setOutgoingConnectionRequests((new ArrayList<>()));
            user.setIncomingConnectionRequests(new ArrayList<>());
            updateUser(user);
        }
    }
}
