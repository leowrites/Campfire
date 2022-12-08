package usecases.requestconnect.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
