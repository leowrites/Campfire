package user.connect.exceptions;

public class PendingRequestExistsException extends Exception{
    public PendingRequestExistsException(String msg){
        super(msg);
    }
}
