package user.comment;

public class CommentRequestModel {
    private final String userId;
    private int reviewId;
    private final String content;

    public CommentRequestModel(String userId, int reviewId, String content) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.content = content;
    }
    public String getUserId() {
        return this.userId;
    }

    public int getReviewId() {
        return this.reviewId;
    }

    public String getContent() {
        return this.content;
    }

}
