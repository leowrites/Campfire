package user.requestconnect;
import java.util.*;

public class RequestConnectionResponseModel {
    private final String connectionStatus;
    private final Date connectionTime;
    private String message;
    public RequestConnectionResponseModel(String connectionStatus){
        this.connectionStatus = connectionStatus;
        this.connectionTime = new Date();
    }
    public RequestConnectionResponseModel(String connectionStatus, String message){
        this.connectionStatus = connectionStatus;
        this.connectionTime = new Date();
        this.message = message;
    }
    public String getConnectionStatus() {
        return connectionStatus;
    }

    public Date getConnectionTime() {
        return connectionTime;
    }
    public String getMessage() {
        return message;
    }
}
