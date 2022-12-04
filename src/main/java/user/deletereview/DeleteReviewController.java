package user.deletereview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DeleteReviewController {

    final IDeleteReviewInput interactor;

    @Autowired
    public DeleteReviewController(IDeleteReviewInput interactor){
        this.interactor = interactor;
    }
    @DeleteMapping("/corporates/{corporateId}/internships/{internshipId}/reviews/{reviewId}")
    public ResponseEntity<DeleteReviewResponseModel> createDeleteReviewRequestModel(
            @RequestBody DeleteReviewRequestModel requestModel,
            Principal principal){
        if (!principal.getName().equals(requestModel.getUserId())) {
            return new ResponseEntity<>(new DeleteReviewResponseModel("Unauthorized!"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(interactor.deleteReview(requestModel), HttpStatus.OK);
    }
}
