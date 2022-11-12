package representation;
import entity.User;

public class ConnectionResponseRepresentation {
    private final User user;
    private final String responseMessage;
    public ConnectionResponseRepresentation(User user, String responseMessage) {
        this.user = user;
        this.responseMessage = responseMessage;
    }

    public User getUser() {
        return user;
    }

    public String getResponse() {
        return responseMessage;
    }
}
