package user.deletecomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteCommentController {

    final IDeleteCommentInput interactor;

    @Autowired
    public DeleteCommentController(IDeleteCommentInput interactor){
        this.interactor = interactor;
    }

    @DeleteMapping("/corporates/{corporateId}/internships/{internshipId}/reviews/{reviewId}/comments")
    public DeleteCommentResponseModel createDeleteCommentRequestModel(
            @RequestBody DeleteCommentRequestModel requestModel){
        return interactor.createResponseModel(requestModel);
    }
}
