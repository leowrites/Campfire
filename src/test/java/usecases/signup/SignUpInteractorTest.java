package usecases.signup;

import entity.User;
import main.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import service.dao.IRoleDAO;
import service.dao.IUserDAO;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SignUpInteractorTest {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private ISignUp signUpInteractor;
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        signUpInteractor = new SignUpInteractor(userDAO, roleDAO, passwordEncoder);
    }

    //Test validateInputs with a weak password
    @Test
    public void testValidateWeakPassword(){
        SignUpRequestModel weakPasswordRequest = new SignUpRequestModel("Test",
                "Test",
                "123",
                "123",
                "test@mail.utoronto.ca",
                "test");
        SignUpResponseModel response = this.signUpInteractor.validateInputs(weakPasswordRequest);
        assertEquals(response.getErrorMessages().get(0).getMessage(), "Please ensure at least one: capital, lowercase," +
                " number, special characters, and a minimum length of 8 because we hate you :)");
    }

    public void correctValidation(){
        SignUpRequestModel weakPasswordRequest = new SignUpRequestModel("Test",
                "Test",
                "TestPassword5%",
                "TestPassword5%",
                "test@mail.utoronto.ca",
                "test");
        SignUpResponseModel response = this.signUpInteractor.validateInputs(weakPasswordRequest);
        assertTrue(response.getErrorMessages().isEmpty());
        // check if user is saved to database
        try {
            User retrievedUser = userDAO.getUser("test");
            assertEquals(retrievedUser.getEmail(), "test@mail.utoronto.ca");
        } catch (Exception e){
            System.out.println("This should never happen");
        }
    }

}
