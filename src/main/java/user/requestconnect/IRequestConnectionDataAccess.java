package user.requestconnect;
import entity.User;

public interface IRequestConnectionDataAccess {
    // returns a serialized user object?
    User getUser(String userId);
    void saveUser(User user);
    void updateUser(User user);
}
