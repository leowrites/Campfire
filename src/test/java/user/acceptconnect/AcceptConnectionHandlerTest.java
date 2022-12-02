package user.acceptconnect;

import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.dao.IUserDAO;
import user.acceptconnect.exceptions.NoRequestFoundException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import user.requestconnect.exceptions.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AcceptConnectionHandlerTest {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private AcceptConnectionHandler acceptConnectionHandler;
    private User user;
    private User target;

    @BeforeEach
    public void init() {
        user = new User("leo", "leo@gmail.com", "pass", "Leo");
        target = new User("justin", "justin@gmail.com", "pass", "Justin");
        acceptConnectionHandler = new AcceptConnectionHandler(user, target, userDAO);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (username varchar(50) primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM users");
    }

    @Test
    public void testAcceptConnectionWithValidInputs() {
        user.getIncomingConnectionRequests().add(target.getUsername());
        target.getOutgoingConnectionRequests().add(user.getUsername());
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        try {
            acceptConnectionHandler.acceptConnection();
        } catch (UserAlreadyConnectedException | NoRequestFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            User dbUser = userDAO.getUser(user.getUsername());
            User dbTarget = userDAO.getUser(target.getUsername());
            assertEquals(target.getUsername(), dbUser.getConnections().get(0));
            assertEquals(user.getUsername(), dbTarget.getConnections().get(0));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAcceptConnectionThrowsUserAlreadyConnectedException() {
        user.getConnections().add(target.getUsername());
        target.getConnections().add(user.getUsername());
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () ->
                acceptConnectionHandler.acceptConnection());
        assertEquals(String.format("%s and %s are already connected!", user.getUsername(),
                target.getUsername()), exception.getMessage());
    }

    @Test
    public void testAcceptConnectionThrowsNoRequestFoundException() {
        userDAO.saveUser(user);
        Throwable exception = assertThrows(NoRequestFoundException.class, () ->
                acceptConnectionHandler.acceptConnection());
        assertEquals(String.format("No request from %s found!", target.getUsername()), exception.getMessage());
    }
}
