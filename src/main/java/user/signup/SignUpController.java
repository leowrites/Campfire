package user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ComponentScan("main")
@RestController
public class SignUpController{

    private SignUpInputBoundary signUpInteractor;

    @Autowired
    public SignUpController(SignUpInputBoundary signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    @PostMapping("/user/signup")
    public void receiveSignUpForm(@RequestBody SignUpInputDS inputdata) {

    }
}

