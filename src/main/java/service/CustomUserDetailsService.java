package service;

import entity.Privilege;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import entity.User;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IRoleDAO;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserDAO userDataAccess;

    @Autowired
    private IRoleDAO roleDAO;
    /**
     *
     * Loads UserDetails based on the username provided
     * @param username the username identifying the user whose data is required.
     * @return an instance of UserDetails based on the username provided
     * @throws UsernameNotFoundException if no user is found
     *
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userDataAccess.getUser(username);

            return new CustomUserDetails(user.getPassword(), user.getUsername(), true,
                    true, true, true,
                    getAuthorities(user.getRole()));
        } catch(UserNotFoundException e){
            throw new UsernameNotFoundException("No username found");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
