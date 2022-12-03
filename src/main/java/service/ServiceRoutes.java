package service;

import com.google.gson.Gson;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dao.*;
import user.comment.exceptions.ReviewNotFoundException;
import user.createcorporate.exceptions.CompanyNotFoundException;
import user.exceptions.InternshipNotFoundException;
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
    @Autowired
    private ICorporateDAO corporateDAO;
    @Autowired
    private IInternshipDAO internshipDAO;

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

    // get internship details
    @GetMapping("/corporates/{corporateId}/internships/{internshipId}")
    public ResponseEntity<Internship> getInternshipDetails(
            @PathVariable String internshipId) {
        try {
            return new ResponseEntity<>(internshipDAO.getInternshipByID(Integer.parseInt(internshipId))
                    , HttpStatus.OK);
        } catch (InternshipNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // get comments by comment id
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable String commentId) {
        return new ResponseEntity<>(commentDAO.getComment(Integer.parseInt(commentId)), HttpStatus.OK);
    }

    // get all corporates
    @GetMapping("/corporates")
    public ResponseEntity<ArrayList<Corporate>> getCorporates() {
        return new ResponseEntity<>(corporateDAO.getAllCorporates(), HttpStatus.OK);
    }

    @GetMapping("/corporates/{corporateId}")
    public ResponseEntity<Corporate> getCorporateDetails(
            @PathVariable String corporateId
    ){
        try{
            Corporate corporate = corporateDAO.getCorporate(Integer.parseInt(corporateId));
            return new ResponseEntity<>(corporate, HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/corporates/{corporateId}/internships")
    public ResponseEntity<ArrayList<Internship>> getCorporateInternships(
            @PathVariable String corporateId
    ){
        try{
            ArrayList<Internship> internships = internshipDAO.getInternshipsByCompany(Integer.parseInt(corporateId));
            return new ResponseEntity<>(internships, HttpStatus.OK);
        } catch (InternshipNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/corporates/{corporateId}/internships/{internshipId}/reviews")
    public ResponseEntity<ArrayList<Review>> getInternshipReviews(
            @PathVariable String internshipId
    ){
        try{
            ArrayList<Review> reviews = reviewDAO.getReviewsByInternship(Integer.parseInt(internshipId));
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (ReviewNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/corporates/{corporateId}/internships/{internshipId}/reviews/{parentId}")
    public ResponseEntity<ArrayList<Comment>> getCommentsByParent(@PathVariable String parentId){
        ArrayList<Comment> comment = commentDAO.getCommentsWithParentId(Integer.parseInt(parentId));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
