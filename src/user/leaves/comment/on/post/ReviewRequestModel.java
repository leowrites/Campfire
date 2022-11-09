package user.leaves.comment.on.post;

public class ReviewRequestModel {
    private String username;
    private String comment;

    public ReviewRequestModel() {
        this.username = "";
        this.comment = "";
    }

    public ReviewRequestModel(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

}


