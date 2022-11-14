package user_sign_up;

public interface SignUpOutputBoundary {

    SignUpOutputDS getSuccessOutput();

    SignUpOutputDS getFailureOutput();
}
