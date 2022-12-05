package user.postreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** The postreview use case controller that connects to Spring. Takes in an InternshipId and a
 * PostReviewRequest from the user input in front-end, creates a PostReviewResponse by sending
 * the request model to the interactor, and puts the response model in a ResponseEntity with the
 * http status to send back to the front-end.
 */
@RestController
public class PostReviewController {
    IPostReview postReview;
    @Autowired
    public PostReviewController(IPostReview postReview) {
        this.postReview = postReview;
    }

    /** Creates a PostReviewResponse using the inputs in request.
     * @param internshipId the String representation of the id of the internship taken in from the
     *                     front-end
     * @param request the PostReviewRequest taken in from the front-end
     * @return a ResponseEntity holding a PostReviewResponse and an HttpStatus
     */
    @PostMapping("/corporates/{corporateId}/internships/{internshipId}/reviews")
    public ResponseEntity<PostReviewResponse> addReviewToCorporate(
            @PathVariable("internshipId") String internshipId,
            @RequestBody PostReviewRequest request) {
        request.setInternshipId(internshipId);
        return new ResponseEntity<>(postReview.addReviewToInternship(request), HttpStatus.CREATED);
    }
}
