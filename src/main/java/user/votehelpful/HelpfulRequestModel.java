package user.votehelpful;

/** A request model for the votehelpful use case that frames the input data into an object. Holds
 * a boolean representation of if the review is helpful or not in isHelpful, and an int
 * representation of the review's id in reviewId.
 */
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
