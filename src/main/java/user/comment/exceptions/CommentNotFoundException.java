package user.comment.exceptions;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
