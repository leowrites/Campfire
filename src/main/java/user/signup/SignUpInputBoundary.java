package user.signup;

// use case layer

public interface SignUpInputBoundary {

    SignUpPresenterResponseModelDS validateInputs(SignUpInputDS signUpInputs);
}
