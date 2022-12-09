package usecases.createinternship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ServerStatus;

/** The createinternship use case controller that connects to Spring. Takes in a CreateInternshipInputDS
 * request model from the user input in front-end, creates a CreateInternshipOutputDS response model by sending
 * the request model to the interactor, and puts the response model in a ResponseEntity with the http status
 * to send back to the front-end.
 */
@ComponentScan("main")
@RestController
public class CreateInternshipController {

    private final CreateInternshipInputBoundary interactor;

    /** This looks for the CreateInternshipConfig class to get the interactor.
     * @param interactor a CreateInternshipInteractor.
     */
    @Autowired
    public CreateInternshipController(CreateInternshipInputBoundary interactor) {
        this.interactor = interactor;
    }

    /** Creates a CreateInternshipResponseDS response model using the inputs in inputDS.
     * @param inputDS the CreateInternshipInputDS request model taken in from the front-end
     * @return a ResponseEntity holding a CreateInternshipResponseDS and an HttpStatus
     */
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

