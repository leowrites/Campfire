package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import entity.User;
import user.requestconnect.exceptions.UserNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDataAccess userDataAccess;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user ;
        try{
            user = userDataAccess.getUser(username);
        } catch(UserNotFoundException e){
            throw new UsernameNotFoundException("No username found");
        }
        return new CustomUserDetails(
                user, user.getPassword(), user.getUsername()
        );
    }
}
