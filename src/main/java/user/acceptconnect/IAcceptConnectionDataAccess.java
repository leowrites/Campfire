package user.acceptconnect;

import entity.User;
import user.requestconnect.exceptions.UserNotFoundException;

public interface IAcceptConnectionDataAccess {
    User getUser(String userId) throws UserNotFoundException;
    void saveUser(User user);
    void updateUser(User user);
}
