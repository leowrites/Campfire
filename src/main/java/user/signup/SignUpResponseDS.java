package user.signup;

//Data structure that SignUpPresenter creates and sends.

import entity.FieldError;

import java.util.List;

public class SignUpResponseDS {
    private final List<FieldError> errorMessages;
    public SignUpResponseDS(List<FieldError> errorMessages) {
        this.errorMessages = errorMessages;
    }
    public List<FieldError> getErrorMessages() {
        return errorMessages;
    }
}
