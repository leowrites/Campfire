package usecases.postreview;

import java.util.UUID;

/** A request model for the postreview use case that frames the input data into an object.
 * Holds the String representing the id of the internship in internshipId, the String content of
 * the review in reviewContent, the String username of the user posting the review in username,
 * and the int rating on the review in rating.
 */
public class PostReviewRequest {
    private UUID internshipId;
    private final String reviewContent;
    private String username;
    private final int rating;

    public PostReviewRequest(String reviewContent,
                             UUID internshipId,
                             int rating) {
        this.reviewContent = reviewContent;
        this.internshipId = internshipId;
        this.rating = rating;
    }
    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }
    public UUID getInternshipId() {
        return internshipId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
