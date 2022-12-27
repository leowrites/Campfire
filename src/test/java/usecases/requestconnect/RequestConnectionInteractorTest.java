package usecases.requestconnect;

import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.ServerStatus;
import service.dao.IUserDAO;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RequestConnectionInteractorTest {

    @Autowired
    private IUserDAO userDAO;
    private RequestConnectionInteractor requestConnectionInteractor;
    private User user;
    private User target;

    @BeforeEach
    public void init() {
        requestConnectionInteractor = new RequestConnectionInteractor(userDAO);
        user = new User("leo", "leo@gmail.com", "pass", "Leo");
        target = new User("justin", "justin@gmail.com", "pass", "Justin");
        userDAO.saveUser(user);
        userDAO.saveUser(target);
    }

    @Test
    @Transactional
    public void testRequestConnectionWithValidConnectionRequest() {
        RequestConnectionRequestModel requestModel = new RequestConnectionRequestModel(
                user.getUsername(), target.getUsername()
        );
        RequestConnectionResponseModel responseModel = requestConnectionInteractor.requestConnection(requestModel);
        assertEquals(ServerStatus.SUCCESS, responseModel.getServerStatus());
        assertThat(target.getOutgoingConnectionRequests())
                .usingRecursiveComparison()
                .isEqualTo(responseModel.getTargetResponseModel().getOutgoingConnectionRequests());
        assertThat(user.getIncomingConnectionRequests())
                .usingRecursiveComparison()
                .isEqualTo(responseModel.getUserResponseModel().getIncomingConnectionRequests());
        assertEquals(Action.OUTGOING_CONNECT_REQUEST,responseModel.getUserResponseModel().getAction());
        assertEquals(Action.INCOMING_CONNECT_REQUEST, responseModel.getTargetResponseModel().getAction());
    }

    @Test
    @Transactional
    public void testRequestConnectionWhenAlreadyConnected() {
        user.getConnectedUsers().add(target);
        target.getConnectedUsers().add(user);
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
    @Transactional
    public void testRequestConnectionWithPendingRequest() {
        RequestConnectionRequestModel requestModel = new RequestConnectionRequestModel(
                user.getUsername(), target.getUsername()
        );
        requestConnectionInteractor.requestConnection(requestModel);
        RequestConnectionResponseModel responseModel = requestConnectionInteractor.requestConnection(requestModel);
        assertEquals( ServerStatus.ERROR, responseModel.getServerStatus());
        assertEquals("Pending request!", responseModel.getUserResponseModel().getMessage());
    }

    @Test
    @Transactional
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
