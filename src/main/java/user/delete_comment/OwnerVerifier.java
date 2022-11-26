package user.delete_comment;

import user.delete_comment.exceptions.NotOwnCommentException;
import entity.Comment;

public class OwnerVerifier {

    private final Comment comment;

    private final String userId;

    public OwnerVerifier(Comment comment, String userId){
        this.comment = comment;
        this.userId = userId;
    }

    public void verify() throws NotOwnCommentException{
        // Raises error if the two ids are not equal to each other (Comment does not belong to user)
        String userIdComment = this.comment.getUserID();
        if (!this.userId.equals(userIdComment)) {
            throw new NotOwnCommentException("Comment does not belong to user");
        }

    }
}
