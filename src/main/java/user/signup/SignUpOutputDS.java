package user.signup;

//Data Structure that SignUpPresenter receives

public class SignUpOutputDS {
    private String errorMessage;
    private String userSession;

    public SignUpOutputDS(String errorMessage, String userSession) {
        this.errorMessage = errorMessage;
        this.userSession = userSession;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUserSession() {
        return userSession;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }
}
