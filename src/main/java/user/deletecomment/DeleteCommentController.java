package user.deletecomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class DeleteCommentController {

    final IDeleteCommentInput interactor;

    @Autowired
    public DeleteCommentController(IDeleteCommentInput interactor){
        this.interactor = interactor;
    }

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
