package usecases.comment.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
