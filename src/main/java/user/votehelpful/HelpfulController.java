package user.votehelpful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** The votehelpful use case controller that connects to Spring. Takes in a HelpfulRequestModel
 * from the user input in front-end, creates a HelpfulResponseModel by sending the criteria to
 * the interactor, puts the response model in a ResponseEntity with the http status to send
 * back to the front-end.
 */
@RestController
public class HelpfulController {
    private final IHelpfulInputBoundary input;

    public HelpfulController(IHelpfulInputBoundary input) {
        this.input = input;
    }

    /** Creates a HelpfulResponseModel using the inputs in requestModel
     * @param requestModel the HelpfulRequestModel taken in from the front-end
     * @return a ResponseEntity holding a HelpfulResponseModel and an HttpStatus
     */
    @PostMapping("/vote-helpful")
    public ResponseEntity<HelpfulResponseModel> create(@RequestBody HelpfulRequestModel requestModel) {
        HelpfulResponseModel responseModel = input.create(requestModel);
        String status = responseModel.getStatus().toString();
        if (status.equals("Success")) {
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }
}
