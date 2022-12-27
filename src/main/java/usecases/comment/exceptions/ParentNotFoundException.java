package usecases.comment.exceptions;

public class ParentNotFoundException extends RuntimeException {
    public ParentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}