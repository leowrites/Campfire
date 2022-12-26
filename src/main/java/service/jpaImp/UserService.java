package service.jpaImp;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Primary
public class UserService implements IUserDAO {
    @Autowired
    private UserRepository userRepository;

    /**
     * Returns a User object given username.
     *
     * @param username the username of the query
     * @return a User object
     */
    @Override
    @Transactional(readOnly = true)
    public User getUser(String username) throws UserNotFoundException {
        Optional<User> userResponse = userRepository.findById(username);
        if (userResponse.isPresent()){
            return userResponse.get();
        } else {
            throw new UserNotFoundException(String.format("No user with name %s found", username));
        }
    }

    /**
     * Save a new User object.
     *
     * @param user the User object to be stored
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Updates a User object.
     *
     * @param user the new User object
     */
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * Gets all users in the User database.
     *
     * @return an ArrayList of all the User objects in the database
     */
    @Override
    public ArrayList<User> getUsers() {
        return null;
    }

    /**
     * Resets all the User objects.
     */
    @Override
    public void reset() {

    }
}
