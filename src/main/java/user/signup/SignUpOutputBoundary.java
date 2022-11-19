package user.signup;

public interface SignUpOutputBoundary {

    SignUpOutputDS getSuccessOutput();

    SignUpOutputDS getFailureOutput();
}
