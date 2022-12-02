package user.createcorporate.exceptions;

public class CorporateCreationFailedException extends RuntimeException {
    /**
     * This exception is thrown when a corporate already exists and a new corporate can't be created
     * @param error is the error message that describes the error
     */
    public CorporateCreationFailedException(String error){super(error);}
}
