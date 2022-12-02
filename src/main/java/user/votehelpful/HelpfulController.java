package user.votehelpful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpfulController {
    private final IHelpfulInputBoundary input;

    public HelpfulController(IHelpfulInputBoundary input) {
        this.input = input;
    }

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
