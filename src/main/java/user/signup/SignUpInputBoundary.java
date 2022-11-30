package user.signup;

// use case layer

public interface SignUpInputBoundary {

    SignUpResponseDS validateInputs(SignUpInputDS signUpInputs);
}
