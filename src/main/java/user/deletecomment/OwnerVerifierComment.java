package user.deletecomment;

import user.exceptions.NotOwnCommentException;
import entity.Comment;

public class OwnerVerifierComment {

    private final Comment comment;

    private final String userId;

    public OwnerVerifierComment(Comment comment, String userId){
        this.comment = comment;
        this.userId = userId;
    }

    public void verify() throws NotOwnCommentException{
        // Raises error if the two ids are not equal to each other (Comment does not belong to user)
        String userIdComment = this.comment.getUserId();
        if (!this.userId.equals(userIdComment)) {
            throw new NotOwnCommentException("Comment does not belong to user");
        }

    }
}
