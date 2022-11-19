package user.signup;

public class SignUpInteractor implements SignUpInputBoundary {

    final SignUpOutputBoundary presenter;
    final SignUpDBGateway dbGateway;

    public SignUpInteractor(SignUpOutputBoundary presenter, SignUpDBGateway dbGateway) {
        this.presenter = presenter;
        this.dbGateway = dbGateway;
    }

    @Override
    public SignUpPresenterResponseModelDS validateInputs(SignUpInputDS signUpInputs) {
        return null;
    }
}
