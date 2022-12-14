package usecases.deletereview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/** The deletereview use case controller that connects to Spring. Takes in a
 * DeleteReviewRequestModel from the user input in front-end, creates a
 * DeleteReviewResponseModel by sending the request model to the interactor, and puts the
 * response model in a ResponseEntity with the http status to send back to the front-end.
 */
@RestController
public class DeleteReviewController {

    final IDeleteReviewInput interactor;

    @Autowired
    public DeleteReviewController(IDeleteReviewInput interactor){
        this.interactor = interactor;
    }

    /** Creates a DeleteReviewResponseModel using the inputs in requestModel.
     * @param principal a Principal object used in Spring security
     * @return a ResponseEntity holding a DeleteReviewResponseModel and an HttpStatus
     */
    @DeleteMapping("/corporates/{corporateId}/internships/{internshipId}/reviews/{reviewId}")
    @PreAuthorize("hasRole('ROLE_AUTHENTICATED_USER')")
    public ResponseEntity<DeleteReviewResponseModel> createDeleteReviewRequestModel(
            @RequestBody DeleteReviewRequestModel requestModel,
            Principal principal){
        // replace with factory
        requestModel.setUsername(principal.getName());
        DeleteReviewResponseModel responseModel = interactor.deleteReview(requestModel);
        if (responseModel.getMessage().equals("Review has successfully been deleted")) {
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }
}
