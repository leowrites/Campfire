package usecases.postreview;

import entity.Review;

public class PostReviewFactory {

    public Review createReview(String userId, String content, int rating) {
        return new Review(userId, content, rating);
    }
}
