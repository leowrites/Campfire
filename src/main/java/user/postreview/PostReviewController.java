package user.postreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostReviewController {
    IPostReview postReview;
    @Autowired
    public PostReviewController(IPostReview postReview) {
        this.postReview = postReview;
    }

    @PostMapping("/corporates/{corporateId}/internships/{internshipId}/reviews")
    public ResponseEntity<PostReviewResponse> addReviewToCorporate(
            @PathVariable("internshipId") String internshipId,
            @RequestBody PostReviewRequest request) {
        request.setInternshipId(internshipId);
        return new ResponseEntity<>(postReview.addReviewToCorporate(request), HttpStatus.CREATED);
    }
}
