package service;

import entity.User;
import user.requestconnect.exceptions.UserNotFoundException;

public interface IUserDataAccess {
    User getUser(String userId) throws UserNotFoundException;
    void saveUser(User user);
    void updateUser(User user);
}
