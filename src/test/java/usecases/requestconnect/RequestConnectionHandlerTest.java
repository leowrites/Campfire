package usecases.requestconnect;

import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RequestConnectionHandlerTest {

    @Autowired
    private IUserDAO userDAO;
    private RequestConnectionHandler requestConnectionHandler;
    private User user;
    private User target;

    @BeforeEach
    public void init() {
        user = new User("leo", "leo@gmail.com", "pass", "Leo");
        target = new User("justin", "justin@gmail.com", "pass", "Justin");
        requestConnectionHandler = new RequestConnectionHandler(user, target, userDAO);
    }

    @Test
    @Transactional
    public void testSendConnectionRequestToTarget() {
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        requestConnectionHandler.sendConnectionRequestToTarget();
        try {
            User dbUser = userDAO.getUser(user.getUsername());
            User dbTarget = userDAO.getUser(target.getUsername());
            assertThat(dbUser)
                    .usingRecursiveComparison()
                    .isEqualTo(user);
            assertThat(dbTarget)
                    .usingRecursiveComparison()
                    .isEqualTo(target);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
