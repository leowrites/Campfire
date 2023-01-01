package service.dao;

import entity.User;
import usecases.requestconnect.exceptions.UserNotFoundException;

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

    User save(User user);

    /** Updates a User object.
     * @param user the new User object
     */
    void updateUser(User user);

    boolean exists(String username);
}
