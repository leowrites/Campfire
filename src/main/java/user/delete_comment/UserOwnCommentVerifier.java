package user.delete_comment;

import user.delete_comment.exceptions.NotOwnCommentException;
import entity.Comment;

public class UserOwnCommentVerifier {

    private final Comment comment;

    private final String userId;

    public UserOwnCommentVerifier(Comment comment, String userId){
        this.comment = comment;
        this.userId = userId;
    }

    public Comment getComment(){
        return this.comment;
    }

    public String getUserId(){
        return this.userId;
    }

    public void verify() throws NotOwnCommentException{
        String userIdGiven = this.getUserId();
        String userIdComment = this.getComment().getUserID();
        if (userIdGiven.equals(userIdComment)) {
            throw new NotOwnCommentException("Comment does not belong to user");
        }

    }
}
