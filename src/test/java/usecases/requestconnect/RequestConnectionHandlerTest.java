package usecases.requestconnect;

import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RequestConnectionHandlerTest {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RequestConnectionHandler requestConnectionHandler;
    private User user;
    private User target;

    @BeforeEach
    public void init() {
        user = new User("leo", "leo@gmail.com", "pass", "Leo");
        target = new User("justin", "justin@gmail.com", "pass", "Justin");
        requestConnectionHandler = new RequestConnectionHandler(user, target, userDAO);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (username varchar(50) primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM users");
    }

    @Test
    public void testSendConnectionRequestToTarget() {
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        requestConnectionHandler.sendConnectionRequestToTarget();
        try {
            User dbUser = userDAO.getUser(user.getUsername());
            User dbTarget = userDAO.getUser(target.getUsername());
            assertEquals(user.getOutgoingConnectionRequests(), dbUser.getOutgoingConnectionRequests());
            assertEquals(target.getIncomingConnectionRequests(), dbTarget.getIncomingConnectionRequests());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
