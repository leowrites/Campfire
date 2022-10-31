package user.connect;
import java.util.*;

public class ConnectionResponseModel {
    public String connectionStatus;
    public Date connectionTime;
    public ConnectionResponseModel(String connectionStatus){
        this.connectionStatus = connectionStatus;
        this.connectionTime = new Date();
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public Date getConnectionTime() {
        return connectionTime;
    }
}
