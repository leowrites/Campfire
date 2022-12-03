package user.postreview;

public interface IPostReview {
    /**
     *
     * @param request the request model
     * @return a response model to be sent back to the client
     */
    PostReviewResponse addReviewToCorporate(PostReviewRequest request);
}
