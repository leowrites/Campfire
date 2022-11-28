package user.postreview;

public class PostReviewRequest {
    private String internshipId;
    private final String reviewContent;
    private final String username;

    public PostReviewRequest(String internshipId, String reviewContent, String username) {
        this.internshipId = internshipId;
        this.reviewContent = reviewContent;
        this.username = username;
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
}