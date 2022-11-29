package main;

import com.google.gson.Gson;
import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IReviewDAO;
import service.IUserDataAccess;
import user.requestconnect.exceptions.UserNotFoundException;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class TestController {
    @Autowired
    private IUserDataAccess userDataAccess;
    @Autowired
    private IReviewDAO reviewDAO;

    @PostMapping("/users/reset")
    public void resetUser() {
        userDataAccess.reset();
    }

    @PostMapping("/users/authenticate")
    public ResponseEntity<String> authenticate(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String response = new Gson().toJson(principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
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

    // for now, get all reviews to display it
    // we will remove this route once internships are set up
    @GetMapping("/reviews")
    public ResponseEntity<ArrayList<Review>> getReviews() {
        return new ResponseEntity<>(reviewDAO.getAllReviews(), HttpStatus.OK);
    }
}
