package usecases.createcorporate.exceptions;

public class CompanyNotFoundException extends Exception{
    public CompanyNotFoundException(String error) {
        super(error);
    }
}
