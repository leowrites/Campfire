package usecases.postreview;

/** A request model for the postreview use case that frames the input data into an object.
 * Holds the String representing the id of the internship in internshipId, the String content of
 * the review in reviewContent, the String username of the user posting the review in username,
 * and the int rating on the review in rating.
 */
public class PostReviewRequest {
    private String internshipId;
    private final String reviewContent;
    private final String username;
    private final int rating;

    public PostReviewRequest(String reviewContent,
                             String username,
                             int rating) {
        this.reviewContent = reviewContent;
        this.username = username;
        this.rating = rating;
    }
    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }
    public String getInternshipId() {
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
}
