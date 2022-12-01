package user.deletereview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteReviewController {

    final IDeleteReviewInput interactor;

    @Autowired
    public DeleteReviewController(IDeleteReviewInput interactor){
        this.interactor = interactor;
    }

    @DeleteMapping("/users/reviews")
    public DeleteReviewResponseModel createDeleteReviewRequestModel(
            @RequestBody DeleteReviewRequestModel requestModel){
        return interactor.createResponseModel(requestModel);
    }
}
