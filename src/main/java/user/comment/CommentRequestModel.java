package user.comment;

public class CommentRequestModel {
    private final String userId;
    private final String reviewId;
    private final String content;

    public CommentRequestModel(String userId, String reviewId, String content) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.content = content;
    }
    public String getUserId() {
        return this.userId;
    }

    public String getReviewId() {
        return this.reviewId;
    }

    public String getContent() {
        return this.content;
    }

}
