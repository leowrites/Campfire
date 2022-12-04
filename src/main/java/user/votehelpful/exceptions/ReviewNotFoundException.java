package user.votehelpful.exceptions;

/** An exception that is thrown when the review with the given id cannot be found.
 */
public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
