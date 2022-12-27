package usecases.postreview;

import entity.Review;

public class PostReviewFactory {

    public Review createReview(String content, int rating) {
        return new Review(content, rating);
    }
}
