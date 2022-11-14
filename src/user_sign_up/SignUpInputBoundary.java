package user_sign_up;

// use case layer

public interface SignUpInputBoundary {

    SignUpPresenterResponseModelDS validateInputs(SignUpInputDS signUpInputs);
}
