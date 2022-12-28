package usecases.deletecomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

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
    @DeleteMapping("/corporates/{corporateId}/internships/{internshipId}/reviews/{reviewId}/comments/{commentId}")
    @PreAuthorize("hasAuthority('ROLE_AUTHENTICATED_USER')")
    public ResponseEntity<DeleteCommentResponseModel> deleteComment(
            @RequestBody DeleteCommentRequestModel requestModel,
            @PathVariable("commentId") UUID commentId,
            Principal principal){

        // this is a temporary fix, this check will go after we start using Spring authorization
        requestModel.setUserId(principal.getName());
        requestModel.setCommentId(commentId);
        DeleteCommentResponseModel responseModel = interactor.deleteComment(requestModel);
        if (responseModel.getMessage().equals("Comment has been successfully deleted")) {
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }
}
