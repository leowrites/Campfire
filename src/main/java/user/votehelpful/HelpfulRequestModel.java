package user.votehelpful;

public class HelpfulRequestModel {
    private final boolean isHelpful;
    private final int reviewId;

    public HelpfulRequestModel(boolean isHelpful, int reviewId) {
        this.isHelpful = isHelpful;
        this.reviewId = reviewId;
    }

    public boolean getIsHelpful() {
        return this.isHelpful;
    }

    public int getReviewId() {
        return this.reviewId;
    }
}
