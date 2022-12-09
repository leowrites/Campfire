package usecases.requestconnect.exceptions;

public class UserAlreadyConnectedException extends Exception{
    public UserAlreadyConnectedException(String msg) {
        super(msg);
    }
}
