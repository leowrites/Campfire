package user.requestconnect;

import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.ServerStatus;
import service.dao.IUserDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RequestConnectionInteractorTest {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RequestConnectionInteractor requestConnectionInteractor;
    private User user;
    private User target;

    @BeforeEach
    public void init() {
        requestConnectionInteractor = new RequestConnectionInteractor(userDAO);
        user = new User("leo", "leo@gmail.com", "pass", "Leo");
        target = new User("justin", "justin@gmail.com", "pass", "Justin");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("CREATE TABLE users (username varchar(50) primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DROP TABLE users");
    }

    @Test
    public void testRequestConnectionWithValidConnectionRequest() {
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        RequestConnectionRequestModel requestModel = new RequestConnectionRequestModel(
                user.getUsername(), target.getUsername()
        );
        RequestConnectionResponseModel responseModel = requestConnectionInteractor.requestConnection(requestModel);
        assertEquals(ServerStatus.SUCCESS, responseModel.getServerStatus());
        assertEquals(target.getUsername(), responseModel.getUserResponseModel().getOutgoingConnectionRequests().get(0));
        assertEquals(user.getUsername(), responseModel.getTargetResponseModel().getIncomingConnectionRequests().get(0));
        assertEquals(Action.OUTGOING_CONNECT_REQUEST,responseModel.getUserResponseModel().getAction());
        assertEquals(Action.INCOMING_CONNECT_REQUEST, responseModel.getTargetResponseModel().getAction());
    }

    @Test
    public void testRequestConnectionWhenAlreadyConnected() {
        user.getConnections().add("justin");
        target.getConnections().add("leo");
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        RequestConnectionRequestModel requestModel = new RequestConnectionRequestModel(
                user.getUsername(), target.getUsername()
        );
        RequestConnectionResponseModel responseModel = requestConnectionInteractor.requestConnection(requestModel);
        assertEquals( ServerStatus.ERROR, responseModel.getServerStatus());
        assertEquals("You are already connected!", responseModel.getUserResponseModel().getMessage());
    }

    @Test
    public void testRequestConnectionWithPendingRequest() {
        userDAO.saveUser(user);
        userDAO.saveUser(target);
        RequestConnectionRequestModel requestModel = new RequestConnectionRequestModel(
                user.getUsername(), target.getUsername()
        );
        requestConnectionInteractor.requestConnection(requestModel);
        RequestConnectionResponseModel responseModel = requestConnectionInteractor.requestConnection(requestModel);
        assertEquals( ServerStatus.ERROR, responseModel.getServerStatus());
        assertEquals("Pending request!", responseModel.getUserResponseModel().getMessage());
    }

    @Test
    public void testRequestConnectionWhenConnectSelf() {
        userDAO.saveUser(user);
        RequestConnectionRequestModel requestModel = new RequestConnectionRequestModel(
                user.getUsername(), user.getUsername()
        );
        requestConnectionInteractor.requestConnection(requestModel);
        RequestConnectionResponseModel responseModel = requestConnectionInteractor.requestConnection(requestModel);
        assertEquals( ServerStatus.ERROR, responseModel.getServerStatus());
        assertEquals("You cannot connect yourself!", responseModel.getUserResponseModel().getMessage());
    }
}
