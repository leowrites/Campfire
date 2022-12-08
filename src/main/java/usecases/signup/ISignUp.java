package usecases.signup;

/**
 * The ISignUp interface defines a use case for validating user input when a user is signing up for an account.
 * It declares a single method, validateInputs, which takes a SignUpRequestModel object as input and returns a
 * SignUpResponseModel object.
 * */

public interface ISignUp {

    SignUpResponseModel validateInputs(SignUpRequestModel signUpInputs);
}
