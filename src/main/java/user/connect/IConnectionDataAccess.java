package user.connect;
import entity.User;

public interface IConnectionDataAccess {
    // returns a serialized user object?
    User getUser(String userId);
    boolean saveUser(User user);
}
