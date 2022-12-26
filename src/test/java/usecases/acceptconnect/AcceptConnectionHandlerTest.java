package usecases.acceptconnect;

import static org.assertj.core.api.Assertions.assertThat;
import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IUserDAO;
import usecases.acceptconnect.exceptions.NoRequestFoundException;
import usecases.requestconnect.exceptions.UserAlreadyConnectedException;
import usecases.requestconnect.exceptions.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AcceptConnectionHandlerTest {

    @Autowired
    private IUserDAO userDAO;
    private AcceptConnectionHandler acceptConnectionHandler;
    private User user;
    private User target;

    @BeforeEach
    public void init() {
        user = new User("leo", "leo@gmail.com", "pass", "Leo");
        target = new User("justin", "justin@gmail.com", "pass", "Justin");
        acceptConnectionHandler = new AcceptConnectionHandler(user, target, userDAO);
    }

    @Test
    @Transactional
    public void testAcceptConnectionWithValidInputs() {
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        user.getIncomingConnectionRequests().add(target);
        target.getOutgoingConnectionRequests().add(user);
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
            assertThat(user).usingRecursiveComparison().isEqualTo(dbUser);
            assertThat(target).usingRecursiveComparison().isEqualTo(dbTarget);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    public void testAcceptConnectionThrowsUserAlreadyConnectedException() {
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        user.getConnectedUsers().add(target);
        target.getConnectedUsers().add(user);
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () ->
                acceptConnectionHandler.acceptConnection());
        assertEquals(String.format("%s and %s are already connected!", user.getUsername(),
                target.getUsername()), exception.getMessage());
    }

    @Test
    @Transactional
    public void testAcceptConnectionThrowsNoRequestFoundException() {
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        Throwable exception = assertThrows(NoRequestFoundException.class, () ->
                acceptConnectionHandler.acceptConnection());
        assertEquals(String.format("No request from %s found!", target.getUsername()), exception.getMessage());
    }
}
