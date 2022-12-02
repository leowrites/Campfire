package service.dao;

import entity.User;
import user.requestconnect.exceptions.UserNotFoundException;

import java.util.ArrayList;

public interface IUserDAO {
    User getUser(String userId) throws UserNotFoundException;
    void saveUser(User user);
    void updateUser(User user);

    ArrayList<User> getUsers();

    void reset();
}
