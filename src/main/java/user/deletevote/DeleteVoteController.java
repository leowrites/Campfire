package user.deletevote;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteVoteController {
    private final IDeleteVoteInput input;

    public DeleteVoteController(IDeleteVoteInput input) {
        this.input = input;
    }

    @PostMapping("/delete-vote")
    public ResponseEntity<DeleteVoteResponseModel> create (@RequestBody DeleteVoteRequestModel requestModel) {
        DeleteVoteResponseModel responseModel = input.create(requestModel);
        String status = responseModel.getStatus().toString();
        if (status.equals("Success")) {
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(responseModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
