package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.*;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<String> authenticate(Principal principal) throws UserNotFoundException, JsonProcessingException {
        User user = userDAO.getUser(principal.getName());
        AuthenticatedUserDto authenticatedUserDto = new ModelMapper().map(user, AuthenticatedUserDto.class);
        String userString = new ObjectMapper().writeValueAsString(authenticatedUserDto);
        return new ResponseEntity<>(userString, HttpStatus.OK);
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
