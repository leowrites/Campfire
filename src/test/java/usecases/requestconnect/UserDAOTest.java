package usecases.requestconnect;

import entity.User;
import main.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDAOTest {
    @Autowired
    IUserDAO userDAO;

    @Test
    @Transactional
    public void testSaveUserAndGetUser() {
        User user = new User(
                "leo", "leo@gmail.com", "pass", "Leo"
        );
        userDAO.saveUser(user);
        try {
            User dbUser = userDAO.getUser(user.getUsername());
            assertThat(dbUser)
                    .usingRecursiveComparison()
                    .isEqualTo(user);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
