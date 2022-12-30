package usecases.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/** The comment use case controller that connects to Spring. Takes in a CommentRequestModel
 * from the user input in front-end, creates a CommentResponseModel by sending the request model
 * to the interactor, and puts the response model in a ResponseEntity with the http status to send
 * back to the front-end.
 */
@RestController
public class CommentController {
    private final ICommentInputBoundary input;

    public CommentController(ICommentInputBoundary input){
        this.input = input;
    }

    /** Creates a CommentResponseModel using the inputs in requestModel.
     * @param requestModel the CommentRequestModel taken in from the front-end
     * @return a ResponseEntity holding a CommentResponseModel and an HttpStatus
     */
    @PostMapping("/corporates/{corporateId}/internships/{internshipId}/reviews/{reviewId}/comments")
    @PreAuthorize("hasRole('ROLE_AUTHENTICATED_USER')")
    public ResponseEntity<CommentResponseModel> create(
            Principal principal,
            @RequestBody CommentRequestModel requestModel) {
        requestModel.setUsername(principal.getName());
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
