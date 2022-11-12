package user.comment;

import entity.Review;

public class ReviewController {
    private final ReviewInputBoundary input;

    public ReviewController(ReviewInputBoundary input){
        this.input = input;
    }

    public ReviewResponseModel create(ReviewRequestModel requestModel) {
        return input.create(requestModel);
    }
}
