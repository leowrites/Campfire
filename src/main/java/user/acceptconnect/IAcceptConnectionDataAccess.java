package user.acceptconnect;

import entity.User;
import user.requestconnect.exceptions.UserNotFoundException;

public interface IAcceptConnectionDataAccess {
    User getUser(String userId) throws UserNotFoundException;
    boolean saveUser(User user);
}
