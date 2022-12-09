package usecases.deletecomment;

import usecases.exceptions.NotOwnCommentException;
import entity.Comment;

/** A class in the deletecomment use case that checks if the user is the owner of the comment, and
 * throws a NotOwnCommentException if the comment is not owned by the user.
 */
public class OwnerVerifierComment {

    private final Comment comment;

    private final String userId;

    public OwnerVerifierComment(Comment comment, String userId){
        this.comment = comment;
        this.userId = userId;
    }

    /** Verifies if comment is owned by the user whose id is userId.
     * @throws NotOwnCommentException thrown when the comment does not belong to the user
     */
    public void verify() throws NotOwnCommentException{
        // Raises error if the two ids are not equal to each other (Comment does not belong to user)
        String userIdComment = this.comment.getUserId();
        if (!this.userId.equals(userIdComment)) {
            throw new NotOwnCommentException("Comment does not belong to user");
        }

    }
}
