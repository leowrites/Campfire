package user.requestconnect;
import entity.User;
import user.requestconnect.exceptions.UserNotFoundException;

public interface IRequestConnectionDataAccess {
    // returns a serialized user object?
    User getUser(String userId) throws UserNotFoundException;
    void saveUser(User user);
    void updateUser(User user);
}
