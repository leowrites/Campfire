package user.connect;
import java.util.*;

public class ConnectionResponseModel {
    private final String connectionStatus;
    private final Date connectionTime;
    private String message;
    public ConnectionResponseModel(String connectionStatus){
        this.connectionStatus = connectionStatus;
        this.connectionTime = new Date();
    }
    public ConnectionResponseModel(String connectionStatus, String message){
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
