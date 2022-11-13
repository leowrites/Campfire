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
    /**
     * @param userId id of the current user
     * @return A serialized user object
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "INSERT INTO users (" +
            "connectionRequests, " +
            "pendingConnections, " +
            "connections, name) " +
            "values (?, ?, ?, ?)";
    final String UPDATE_QUERY = "update user set ? = ? where id = ?";
    @Override
    public User getUser(String userId) {
        /**
         * TODO
         *  need to implement this with mySQL query
         *  this is a temporary placeholder
         */
        User user = null;
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream
                    ("fakeUser.txt");
            ObjectInputStream in = new ObjectInputStream
                    (file);
            // Method for deserialization of object
            user = (User)in.readObject();
            in.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * @param user saves a new connection
     */
    @Override
    public void saveUser(User user){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String connectionRequests = mapper.writeValueAsString(user.getConnectionRequests());
            String pendingConnections = mapper.writeValueAsString(user.getPendingConnections());
            String connections = mapper.writeValueAsString(user.getConnections());
            jdbcTemplate.update(INSERT_QUERY, connectionRequests, pendingConnections, connections, user.getName());
        } catch(JsonProcessingException e){

        }
    }

    @Override
    public void updateUser(ConnectionType connectionType, String values, String id) {
        jdbcTemplate.update(UPDATE_QUERY, connectionType.toString(), values, id);
    }
}

enum ConnectionType{
    CONNECTION_REQUEST, PENDING_CONNECTION, CONNECTIONS;

    @Override
    public String toString() {
        switch(this) {
            case CONNECTION_REQUEST: return "connectionRequests";
            case PENDING_CONNECTION: return "pendingConnections";
            case CONNECTIONS: return "connections";
            default: return null;
        }
    }
}
