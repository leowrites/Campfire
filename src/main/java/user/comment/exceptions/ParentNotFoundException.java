package user.comment.exceptions;

public class ParentNotFoundException extends Exception {
    public ParentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}