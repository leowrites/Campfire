package user.comment;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//follow line 27 on TestController.java, use PostMapping instead of getMapping
@RestController
public class CommentController {
    private final ICommentInputBoundary input;

    public CommentController(ICommentInputBoundary input){
        this.input = input;
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseModel> create(CommentRequestModel requestModel) {
        // create response model using input.create
        // responseModel.getStatus()
        // if  else, creating a new response entity
        // return response entity based on the http status
        return new ResponseEntity<>(input.create(requestModel), HttpStatus.OK); // if failure, should not be HttpStatus.OK, choose approrpiate one
    }
}
