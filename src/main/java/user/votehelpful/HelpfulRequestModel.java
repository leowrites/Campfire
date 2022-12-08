package user.votehelpful;

/** A request model for the votehelpful use case that frames the input data into an object. Holds
 * a String isHelpful corresponding to an ENUM class, and an int
 * representation of the review's id in reviewId.
 */
public class HelpfulRequestModel {
    private final String isHelpful;
    private final int reviewId;
    private final String userId;

    public HelpfulRequestModel(String isHelpful, int reviewId, String userId) {
        this.isHelpful = isHelpful;
        this.reviewId = reviewId;
        this.userId = userId;
    }

    public String getIsHelpful() {
        return this.isHelpful;
    }

    public int getReviewId() {
        return this.reviewId;
    }

    public String getUserId() {
        return this.userId;
    }
}
