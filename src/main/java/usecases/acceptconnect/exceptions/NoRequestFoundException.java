package usecases.acceptconnect.exceptions;

public class NoRequestFoundException extends Exception{
    public NoRequestFoundException(String msg){
        super(msg);
    }
}
