package user.requestconnect;

import entity.User;
import main.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.dao.IUserDAO;
import user.requestconnect.exceptions.UserNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDAOTest {
    @Autowired
    IUserDAO userDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (username varchar(50) primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM users");
    }

    @Test
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
