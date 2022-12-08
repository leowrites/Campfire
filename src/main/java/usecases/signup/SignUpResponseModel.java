package usecases.signup;

import entity.FieldError;
import java.util.List;

/**
 * The SignUpResponseModel class is a data structure that represents the response to a user's sign-up request.
 * It includes a list of FieldError objects that contain information about any errors that were found in the
 * user's input.
 */
public class SignUpResponseModel {
    private final List<FieldError> errorMessages;
    public SignUpResponseModel(List<FieldError> errorMessages) {
        this.errorMessages = errorMessages;
    }
    public List<FieldError> getErrorMessages() {
        return errorMessages;
    }
}
