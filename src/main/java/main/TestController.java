package main;

import com.google.gson.Gson;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IUserDataAccess;
import user.requestconnect.exceptions.UserNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class TestController {
    @Autowired
    private IUserDataAccess userDataAccess;

    @GetMapping("/users")
    public ResponseEntity<ArrayList<User>> getUser() {
        return new ResponseEntity<>(userDataAccess.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/users/reset")
    public void resetUser() {
        userDataAccess.reset();
    }

    @PostMapping("/users/authenticate")
    public String authenticate(HttpServletResponse response, Principal principal) {
        if (principal == null) {
            return null;
        }
        return new Gson().toJson(principal);
    }

    // get authenticated user info here
    // only allow user to access their own info for now
    @GetMapping("/users/{id}")
    public String getUserInfo(Principal principal, @PathVariable String id) throws UserNotFoundException {
        if (principal == null || !principal.getName().equals(id)) {
            return null;
        }
        return new Gson().toJson(userDataAccess.getUser(principal.getName()));
    }
}
