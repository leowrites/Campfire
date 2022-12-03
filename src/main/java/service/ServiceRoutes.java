package service;

import com.google.gson.Gson;

import entity.Comment;
import entity.Review;
import entity.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import user.deletecomment.DeleteCommentRequestModel;
import user.requestconnect.exceptions.UserNotFoundException;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class ServiceRoutes {
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IReviewDAO reviewDAO;
    @Autowired
    private ICommentDAO commentDAO;

    @PostMapping("/users/reset")
    public void resetUser() {
        userDAO.reset();
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
        return new Gson().toJson(userDAO.getUser(principal.getName()));
    }

    // for now, get all reviews to display it
    // we will remove this route once internships are set up
    @GetMapping("/corporate/{corporateId}/internships/{internshipId}/reviews    ")
    public ResponseEntity<ArrayList<Review>> getReviews() {
        return new ResponseEntity<>(reviewDAO.getAllReviews(), HttpStatus.OK);
    }

    // get comments by comment id
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable String commentId) {
        return new ResponseEntity<>(commentDAO.getComment(Integer.parseInt(commentId)), HttpStatus.OK);
    }

//    @PostMapping("/users/access")
//    public ResponseEntity<User> upgradeAccess(@RequestBody DeleteCommentRequestModel requestModel) {
//        User user;
//        try {
//            user = userDAO.getUser(requestModel.getUserId());
//        } catch(UserNotFoundException e) {
//            System.out.println(e);
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        user.setAccessLevel(1);
//        user.setCorporateRep(true);
//        userDAO.updateUser(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
}
