package service.dao;

import entity.User;
import usecases.requestconnect.exceptions.UserNotFoundException;

import java.util.ArrayList;

/** An interface for the User data access object.
 */
public interface IUserDAO {

    /** Returns a User object given username.
     * @param username the username of the query
     * @return a User object
     */
    User getUser(String username) throws UserNotFoundException;

    /** Save a new User object.
     * @param user the User object to be stored
     */
    void saveUser(User user);

    /** Updates a User object.
     * @param user the new User object
     */
    void updateUser(User user);

    /** Gets all users in the User database.
     * @return an ArrayList of all the User objects in the database
     */
    ArrayList<User> getUsers();

    /** Resets all the User objects.
     */
    void reset();
}
