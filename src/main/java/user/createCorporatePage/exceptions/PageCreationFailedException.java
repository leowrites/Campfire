package user.createCorporatePage.exceptions;

public class PageCreationFailedException extends RuntimeException {
    /**
     * This exception is thrown when a page already exists and a new page can't be created
     * @param error is the error message that describes the error
     */
    public PageCreationFailedException(String error){super(error);}
}
