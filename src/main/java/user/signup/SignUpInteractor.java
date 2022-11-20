package user.signup;
import service.IUserDataAccess;
public class SignUpInteractor implements SignUpInputBoundary {

    final IUserDataAccess dataAccess;

    public SignUpInteractor(IUserDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public SignUpPresenterResponseModelDS validateInputs(SignUpInputDS signUpInputs) {
        return null;
    }
}
