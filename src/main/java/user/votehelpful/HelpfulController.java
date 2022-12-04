package user.votehelpful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** The votehelpful use case controller that connects to Spring. It takes in a HelpfulRequestModel
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

    /** Creates a HelpfulResponseModel using the inputs in requestModel, checks the status of the
     * response model, and returns a ResponseEntity with either an OK or a BAD_REQUEST http status
     * with the response model.
     * @param requestModel the HelpfulRequestModel taken in from the front-end
     * @return a ResponseEntity with a HelpfulResponseModel and an HttpStatus in it.
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
