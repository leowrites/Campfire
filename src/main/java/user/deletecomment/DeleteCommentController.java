package user.deletecomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteCommentController {

    final IDeleteCommentInput interactor;

    @Autowired
    public DeleteCommentController(IDeleteCommentInput interactor){
        this.interactor = interactor;
    }

    @GetMapping("/comments/sort")

    @DeleteMapping("/user/comments")
    public DeleteCommentResponseModel createDeleteCommentRequestModel(DeleteCommentRequestModel requestModel){
        return interactor.createResponseModel(requestModel);
    }
}
