package service;

import com.google.gson.Gson;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dao.*;
import usecases.comment.exceptions.ReviewNotFoundException;
import usecases.createcorporate.exceptions.CompanyNotFoundException;
import usecases.exceptions.InternshipNotFoundException;
import usecases.requestconnect.exceptions.UserNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class ServiceRoutes {
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IReviewDAO reviewDAO;
    @Autowired
    private ICorporateDAO corporateDAO;
    @Autowired
    private IInternshipDAO internshipDAO;

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
            @PathVariable UUID internshipId) {
        try {
            return new ResponseEntity<>(internshipDAO.getInternship(internshipId)
                    , HttpStatus.OK);
        } catch (InternshipNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // get all corporates
    @GetMapping("/corporates")
    public ResponseEntity<List<Corporate>> getCorporates() {
        return new ResponseEntity<>(corporateDAO.getAllCorporates(), HttpStatus.OK);
    }

    @GetMapping("/corporates/{corporateId}")
    public ResponseEntity<Corporate> getCorporateDetails(
            @PathVariable UUID corporateId
    ){
        try{
            Corporate corporate = corporateDAO.get(corporateId);
            return new ResponseEntity<>(corporate, HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/corporates/{corporateId}/internships")
    public ResponseEntity<List<Internship>> getCorporateInternships(
            @PathVariable UUID corporateId
    ){
        List<Internship> internships = internshipDAO.getInternshipByCompanyId(corporateId);
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/corporates/{corporateId}/internships/{internshipId}/reviews")
    public ResponseEntity<List<Review>> getInternshipReviews(
            @PathVariable UUID internshipId
    ){
        try{
            List<Review> reviews = reviewDAO.getReviewsByInternship(internshipId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (ReviewNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
