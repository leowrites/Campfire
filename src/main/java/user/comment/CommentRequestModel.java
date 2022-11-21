package user.comment;

import java.util.Date;

public class CommentRequestModel {
    private final String id;
    private final String userID;
    private final String reviewID;
    private final String content;

    public CommentRequestModel(String id, String userID, String reviewID, String content) {
        this.id = id;
        this.userID = userID;
        this.reviewID = reviewID;
        this.content = content;
    }

    public String getid(){return this.id;}
    public String getUserID() {
        return this.userID;
    }

    public String getReviewID() {
        return this.reviewID;
    }

    public String getContent() {
        return this.content;
    }

}
