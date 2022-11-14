package user.votehelpful;

public class HelpfulRequestModel {
    private final boolean isHelpful;
    private final String reviewID;

    public HelpfulRequestModel(boolean isHelpful, String reviewID) {
        this.isHelpful = isHelpful;
        this.reviewID = reviewID;
    }

    public boolean getIsHelpful() {
        return this.isHelpful;
    }

    public String getReviewID() {
        return this.reviewID;
    }
}
