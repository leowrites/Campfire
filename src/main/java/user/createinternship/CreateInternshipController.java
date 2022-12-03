package user.createinternship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ServerStatus;

@ComponentScan("main")
@RestController
public class CreateInternshipController {

    private final CreateInternshipInputBoundary interactor;

    @Autowired
    public CreateInternshipController(CreateInternshipInputBoundary interactor) {
        this.interactor = interactor;
    }

    @PostMapping("/corporates/{corporateId}/internships")
    public ResponseEntity<CreateInternshipResponseDS> receiveCreateInternshipForm(@RequestBody CreateInternshipInputDS inputDS){
        CreateInternshipResponseDS responseDS = this.interactor.createInternship(inputDS);
        if (responseDS.getServerStatus().equals(ServerStatus.SUCCESS)){
            return new ResponseEntity<>(responseDS, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(responseDS, HttpStatus.BAD_REQUEST);
        }
    }

}

