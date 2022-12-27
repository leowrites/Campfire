package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import entity.User;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserDAO userDataAccess;

    /**
     *
     * Loads UserDetails based on the username provided
     * @param username the username identifying the user whose data is required.
     * @return an instance of UserDetails based on the username provided
     * @throws UsernameNotFoundException if no user is found
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userDataAccess.getUser(username);
            return new CustomUserDetails(user.getPassword(), user.getUsername());
        } catch(UserNotFoundException e){
            throw new UsernameNotFoundException("No username found");
        }
    }
}
