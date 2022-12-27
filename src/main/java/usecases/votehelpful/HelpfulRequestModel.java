package usecases.votehelpful;

import java.util.UUID;

/** A request model for the votehelpful use case that frames the input data into an object. Holds
 * a boolean representation of if the review is helpful or not in isHelpful, and an int
 * representation of the review's id in reviewId.
 */
public class HelpfulRequestModel {
    private final String isHelpful;
    private final UUID reviewId;
    private final String userId;

    public HelpfulRequestModel(String isHelpful, UUID reviewId, String userId) {
        this.isHelpful = isHelpful;
        this.reviewId = reviewId;
        this.userId = userId;
    }

    public String getIsHelpful() {
        return this.isHelpful;
    }

    public UUID getReviewId() {
        return this.reviewId;
    }

    public String getUserId() {
        return this.userId;
    }
}
