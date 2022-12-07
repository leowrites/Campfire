package user.signup;

// use case layer

public interface ISignUp {

    SignUpResponseModel validateInputs(SignUpRequestModel signUpInputs);
}
