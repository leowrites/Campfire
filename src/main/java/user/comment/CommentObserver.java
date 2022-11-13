package user.comment;

public class CommentObserver {
    private final String userID;

    public CommentObserver(String userID) {
        this.userID = userID;
    }

    public void update() {
        // maybe turn observer into an interface with 2 types, one of the user who made the post and one of users with comments
        // send emails to the creator of the post, and any other users that have left a comment
    }
}
