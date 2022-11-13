package user.connect;
import entity.User;

public interface IConnectionDataAccess {
    // returns a serialized user object?
    User getUser(String userId);
    void saveUser(User user);

    void updateUser(ConnectionType connectionType, String values, String id);
}
