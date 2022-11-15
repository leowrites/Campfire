package user.requestconnect;
import entity.User;
import java.util.*;

public class RequestConnectionResponseModel {
    private final ServerStatus connectionStatus;
    private String message;
    private User user;
    private User target;
    public RequestConnectionResponseModel(ServerStatus connectionStatus, User user, User target){
        this.connectionStatus = connectionStatus;
        this.user = user;
        this.target = target;
    }
    public RequestConnectionResponseModel(ServerStatus connectionStatus, String message){
        this.connectionStatus = connectionStatus;
        this.message = message;
    }
    public ServerStatus getConnectionStatus() {
        return connectionStatus;
    }
    public User getUser() {
        return user;
    }

    public User getTarget() {return target;}

    public String getMessage() {
        return message;
    }
}
