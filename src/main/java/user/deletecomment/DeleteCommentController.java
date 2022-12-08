package user.deletecomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/** The deletecomment use case controller that connects to Spring. Takes in a
 * DeleteCommentRequestModel from the user input in front-end, creates a
 * DeleteCommentResponseModel by sending the request model to the interactor, and puts the
 * response model in a ResponseEntity with the http status to send back to the front-end.
 */
@RestController
public class DeleteCommentController {

    final IDeleteCommentInput interactor;

    @Autowired
    public DeleteCommentController(IDeleteCommentInput interactor){
        this.interactor = interactor;
    }

    /** Creates a DeleteCommentResponseModel using the inputs in requestModel.
     * @param requestModel the DeleteCommentRequestModel taken in from the front-end
     * @param principal a Principal object used in Spring security
     * @return a ResponseEntity holding a DeleteCommentResponseModel and an HttpStatus
     */
    @DeleteMapping("/corporates/{corporateId}/internships/{internshipId}/reviews/{reviewId}/comments")
    public ResponseEntity<DeleteCommentResponseModel> deleteComment(
            @RequestBody DeleteCommentRequestModel requestModel,
            Principal principal){
        // reject the request right away if principal is not authorized
        if (!principal.getName().equals(requestModel.getUserId())) {
            return new ResponseEntity<>(new DeleteCommentResponseModel(
                    "Unauthorized!"
            ), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(interactor.deleteComment(requestModel), HttpStatus.OK);
    }
}
