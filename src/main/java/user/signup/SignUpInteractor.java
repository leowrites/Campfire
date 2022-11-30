package user.signup;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import service.dao.IUserDAO;

import entity.FieldError;
import user.requestconnect.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignUpInteractor implements SignUpInputBoundary {

    @Autowired
    final IUserDAO dataAccess;
    @Autowired
    PasswordEncoder passwordEncoder;

    public SignUpInteractor(IUserDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public SignUpResponseDS validateInputs(SignUpInputDS signUpInputs) {
        List<FieldError> errorMessages = new ArrayList<FieldError>();

//        System.out.println("test");
//        //validate email is a valid U of T Email Address
        if (!signUpInputs.getEmail().matches("^[A-Za-z0-9._%+-]+@mail\\.utoronto\\.ca$")){
            errorMessages.add(new FieldError("email", "Please enter a valid email"));
        }

        //validate username is unique
        try {
            dataAccess.getUser(signUpInputs.getUsername());
            errorMessages.add(new FieldError("username", "username taken"));
        } catch (UserNotFoundException e) {
            // this is good, we proceed
        }

        //validate email is unique

        //validate password and confirmPassword matches
        if (!signUpInputs.getPassword().equals(signUpInputs.getConfirmPassword())){
            errorMessages.add( new FieldError("confirmPassword", "Passwords don't match"));
        }

        //validate password strength - at least one upper case, one lower case, one number, one special character,
        //length > 8
        if (!signUpInputs.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
            errorMessages.add(new FieldError("password", "Please ensure at least one: capital, lowercase," +
                    " number, special characters, and a minimum length of 8 because we hate you :)"));
        }

        //save user in database if there are no errors
        if (errorMessages.size() == 0){
            User user = new User(
                    signUpInputs.getUsername(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    signUpInputs.getEmail(),
                    signUpInputs.getUsername(),
                    passwordEncoder.encode(signUpInputs.getPassword()),
                    signUpInputs.getFirstName()
            );
            dataAccess.saveUser(user);
        }
        return new SignUpResponseDS(errorMessages);
    }
}
