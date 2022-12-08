package usecases.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The SignUpController class is a REST controller for handling sign-up requests from users.
 * It receives a SignUpRequestModel object containing the user's sign-up information and passes
 * it to the ISignUp interactor for validation.
 * If the input is valid, the controller returns a SignUpResponseModel object with a success status.
 * If there are any errors in the input, the controller returns a SignUpResponseModel object with a list of
 * error messages and a failure status.
 * */
@ComponentScan("main")
@RestController
public class SignUpController{

    private final ISignUp interactor;

    @Autowired
    public SignUpController(ISignUp signUpInteractor) {
        this.interactor = signUpInteractor;
    }

    /**
     * The receiveSignUpForm method is a handler for HTTP POST requests to the /signup endpoint.
     * It takes a SignUpRequestModel object as input and passes it to the ISignUp interactor's validateInputs
     * method for validation. If the input is valid, the method returns a ResponseEntity object containing the
     * SignUpResponseModel with a success status (HTTP status code 200 OK). If there are errors in the input,
     * the method returns a ResponseEntity object containing the SignUpResponseModel with a failure status
     * (HTTP status code 422 Unprocessable Entity).
     * */
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseModel> receiveSignUpForm(@RequestBody SignUpRequestModel inputdata) {
        SignUpResponseModel responseDS = this.interactor.validateInputs(inputdata);
        if (responseDS.getErrorMessages().isEmpty()){
            return new ResponseEntity<>(responseDS, HttpStatus.OK);
        } else {return new ResponseEntity<>(responseDS, HttpStatus.UNPROCESSABLE_ENTITY);}
    }
}

