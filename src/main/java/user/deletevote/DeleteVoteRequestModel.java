package user.deletevote;

public class DeleteVoteRequestModel {
    private final int reviewId;
    private final String userId;

    public DeleteVoteRequestModel(int reviewId, String userId) {
        this.reviewId = reviewId;
        this.userId = userId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getUserId() {
        return userId;
    }
}
