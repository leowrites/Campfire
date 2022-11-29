package user.postreview;

public class PostReviewRequest {
    private final String internshipId;
    private final String reviewContent;
    private final String username;
    private final int rating;

    public PostReviewRequest(String internshipId, String reviewContent, String username, int rating) {
        this.internshipId = internshipId;
        this.reviewContent = reviewContent;
        this.username = username;
        this.rating = rating;
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