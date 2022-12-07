package user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan("main")
@RestController
public class SignUpController{

    private final ISignUp interactor;

    @Autowired
    public SignUpController(ISignUp signUpInteractor) {
        this.interactor = signUpInteractor;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseModel> receiveSignUpForm(@RequestBody SignUpRequestModel inputdata) {
        SignUpResponseModel responseDS = this.interactor.validateInputs(inputdata);
        if (responseDS.getErrorMessages().isEmpty()){
            return new ResponseEntity<>(responseDS, HttpStatus.OK);
        } else {return new ResponseEntity<>(responseDS, HttpStatus.UNPROCESSABLE_ENTITY);}
    }
}

