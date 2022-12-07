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

        // this is a temporary fix, this check will go after we start using Spring authorization
        requestModel.setUserId(principal.getName());
        DeleteCommentResponseModel responseModel = interactor.deleteComment(requestModel);
        if (responseModel.getMessage().equals("Comment has been successfully deleted")) {
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }
}
