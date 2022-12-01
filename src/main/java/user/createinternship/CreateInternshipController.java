package user.createinternship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan("main")
@RestController
public class CreateInternshipController {

    private final CreateInternshipInputBoundary interactor;

    @Autowired
    public CreateInternshipController(CreateInternshipInputBoundary interactor) {
        this.interactor = interactor;
    }

    @PostMapping("/users/createInternship")
    public ResponseEntity<CreateInternshipResponseDS> receiveCreateInternshipForm(@RequestBody CreateInternshipInputDS inputDS){
        CreateInternshipResponseDS responseDS = this.interactor.createInternship(inputDS);
        if (responseDS.getSuccess_status().equals("success")){
            return new ResponseEntity<>(responseDS, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(responseDS, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users/test")
    public ResponseEntity<CreateInternshipResponseDS> testGetInternship(@RequestBody CreateInternshipInputDS inputDS){
        System.out.println("?");
        this.interactor.test();
        return new ResponseEntity<>(new CreateInternshipResponseDS("fuck"), HttpStatus.OK);
    }
}

