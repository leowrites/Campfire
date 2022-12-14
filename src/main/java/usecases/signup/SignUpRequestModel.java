package usecases.signup;

/**
 * The SignUpRequestModel class is a data structure that represents the information
 * that a user provides when they are signing up for an account. It includes fields for the user's first and last name,
 * password, confirm password, email, and username. These fields are provided as input to the ISignUpInteractor
 * class's validateInputs method for validation.
 * */
public class SignUpRequestModel {

    private final String firstName;

    private final String lastName;
    private final String password;
    private final String confirmPassword;
    private final String email;

    private final String username;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public SignUpRequestModel(String firstName, String lastName, String password, String confirmPassword, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.username = username;
    }
}
