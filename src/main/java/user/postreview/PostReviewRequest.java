package user.postreview;

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