package user.requestconnect.exceptions;

public class PendingRequestExistsException extends Exception{
    public PendingRequestExistsException(String msg){
        super(msg);
    }
}
