package user.requestconnect;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import user.requestconnect.exceptions.PendingRequestExistsException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;

class RequestConnectionVerifierTest {

    User user1;
    User user2;
    RequestConnectionVerifier verifier;
    @BeforeEach
    public void setUp() {
        user1 = new User("leoliu", "leo@gmail.com", "pass", "Leo");
        user2 = new User("alex123", "alex@gmail.com", "pass", "Alex");
        verifier = new RequestConnectionVerifier(user1, user2);
    }

    @Test
    void testCheckAlreadyConnectedAlreadyConnected() {
        user1.getConnections().add(user2.getUsername());
        user2.getConnections().add(user1.getUsername());
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () -> verifier.checkAlreadyConnected());
        assertEquals("You are already connected!", exception.getMessage());
    }

    @Test
    void testCheckPendingRequestExists() {
        user2.getIncomingConnectionRequests().add(user1.getUsername());
        Throwable exception = assertThrows(PendingRequestExistsException.class, () -> verifier.checkPendingRequest());
        assertEquals("Pending request!", exception.getMessage());
    }

    @Test
    void testCheckIncomingRequestIncomingRequestExists() {
        user1.getIncomingConnectionRequests().add(user2.getUsername());
        assertTrue(verifier.checkIncomingRequest());
    }

    @Test
    void testCheckIncomingRequestIncomingRequestDoesNotExist() {
        assertFalse(verifier.checkIncomingRequest());
    }

    @Test
    void testVerifyAlreadyConnected() {
        user1.getConnections().add(user2.getUsername());
        user2.getConnections().add(user1.getUsername());
        Throwable exception = assertThrows(UserAlreadyConnectedException.class, () -> verifier.checkAlreadyConnected());
        assertEquals("You are already connected!", exception.getMessage());
    }
}