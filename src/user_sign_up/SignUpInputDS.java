package user_sign_up;

//DS that contains field info of the form user submits

public class SignUpInputDS {

    private final String name;
    private final String password;
    private final String confirmPassword;
    private final String email;

    public SignUpInputDS(String name, String password, String confirmPassword, String email) {
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String getName() {
        return name;
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
}
