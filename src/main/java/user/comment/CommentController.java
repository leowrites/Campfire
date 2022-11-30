package user.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final ICommentInputBoundary input;

    public CommentController(ICommentInputBoundary input){
        this.input = input;
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseModel> create(CommentRequestModel requestModel) {
        CommentResponseModel responseModel = input.create(requestModel);
        String status = responseModel.getStatus().toString();
        if (status.equals("success")) {
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }
}
