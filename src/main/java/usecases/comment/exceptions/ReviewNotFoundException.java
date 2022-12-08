package usecases.comment.exceptions;

public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
