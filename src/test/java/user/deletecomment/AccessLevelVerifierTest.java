package user.deletecomment;

import entity.User;
import user.exceptions.NotEnoughAccessLevelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AccessLevelVerifierTest {

    int accessLevelTest;

    User userTest;

    @BeforeEach
    public void setup(){
        userTest = new User();
        userTest.setAccessLevel(0);
        accessLevelTest = userTest.getAccessLevel();
    }

    @Test
    public void testCheckAccessLevel(){
        AccessLevelVerifier accessLevelVerifierTest = new AccessLevelVerifier(accessLevelTest);
        Throwable exception = assertThrows(NotEnoughAccessLevelException.class, accessLevelVerifierTest::verify);
        assertEquals("Not enough access level", exception.getMessage());
    }
}
