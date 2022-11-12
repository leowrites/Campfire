package user.comment;

public class ReviewRequestModel {
    private final String username;
    private final String comment;

    public ReviewRequestModel(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    public String getUsername() {
        return this.username;
    }

    public String getComment() {
        return this.comment;
    }
}
