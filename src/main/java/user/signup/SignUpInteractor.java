package user.signup;
import service.IUserDataAccess;

import entity.FieldError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SignUpInteractor implements SignUpInputBoundary {

    final IUserDataAccess dataAccess;

    public SignUpInteractor(IUserDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public SignUpResponseDS validateInputs(SignUpInputDS signUpInputs) {
        List<FieldError> errorMessages = new ArrayList<FieldError>();

        System.out.println("test");
        //validate email is a valid U of T Email Address
        if (!signUpInputs.getEmail().matches("^[A-Za-z0-9._%+-]+@mail\\.utoronto\\.ca$")){
            errorMessages.add(new FieldError("email", "Please enter a valid email"));
        }

        //validate username is unique

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
        return new SignUpResponseDS(errorMessages);
    }
}
