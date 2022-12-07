package user.sort;

import entity.Review;
import entity.Internship;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.ServerStatus;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SortDAOTest {

    @Autowired
    private IReviewDAO reviewDAO;

    @Autowired
    private IInternshipDAO internshipDAO;

    @Autowired
    private JdbcTemplate jdbctemplate;

    private SortInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new SortInteractor(reviewDAO);
        jdbctemplate.execute("DROP TABLE IF EXISTS reviews");
        jdbctemplate.execute("DROP TABLE IF EXISTS internships");
        jdbctemplate.execute("CREATE TABLE reviews (id serial primary key, data varchar, internshipid integer)");
        jdbctemplate.execute("CREATE TABLE internships (id serial primary key, data varchar, companyid integer)");
    }

    @AfterEach
    public void cleanUp(){
        jdbctemplate.execute("DELETE FROM reviews");
        jdbctemplate.execute("DELETE FROM internships");
    }

    @Test
    public void testSortHelpful(){
        Review review1 = new Review("jtp1", "I hated it", 0);
        Review review2 = new Review("jtp2", "It was bad", 3);
        Review review3 = new Review("jtp3", "It was amazing", 10);
        Review review4 = new Review("jtp4", "Meh", 5);
        Review review5 = new Review("jtp5", "I survived", 7);
        review1.setNumLikes(99);
        review2.setNumLikes(3);
        review3.setNumLikes(15);
        review4.setNumLikes(37);
        review5.setNumLikes(11);

        Internship internship = new Internship(1, new ArrayList<>(), "Dumb", "Jtps boss");

        int internshipId = internshipDAO.saveInternshipAndReturnId(internship);
        int reviewId1 = reviewDAO.saveReview(review1, internshipId);
        int reviewId2 = reviewDAO.saveReview(review2, internshipId);
        int reviewId3 = reviewDAO.saveReview(review3, internshipId);
        int reviewId4 = reviewDAO.saveReview(review4, internshipId);
        int reviewId5 = reviewDAO.saveReview(review5, internshipId);
        internship.setReviews(List.of(reviewId1, reviewId2, reviewId3, reviewId4, reviewId5));

        SortRequestModel requestModel = new SortRequestModel("Helpful", internshipId);
        SortResponseModel responseModel = interactor.createSortResponseModel(requestModel);

        //Test that the interactor returns a success response model.
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Reviews sorted successfully", responseModel.getMessage());

        //Test that the interactor properly sorted the reviews by helpfulness.
        assertEquals(new ArrayList<Integer>(List.of(reviewId1, reviewId4, reviewId3, reviewId5, reviewId2)), responseModel.getSortedOutput());
    }

    @Test
    public void testSortHighestRating(){
        Review review1 = new Review("jtp1", "I hated it", 0);
        Review review2 = new Review("jtp2", "It was bad", 3);
        Review review3 = new Review("jtp3", "It was amazing", 10);
        Review review4 = new Review("jtp4", "Meh", 5);
        Review review5 = new Review("jtp5", "I survived", 7);

        Internship internship = new Internship(1, new ArrayList<>(), "Dumb", "Jtps boss");

        int internshipId = internshipDAO.saveInternshipAndReturnId(internship);
        int reviewId1 = reviewDAO.saveReview(review1, internshipId);
        int reviewId2 = reviewDAO.saveReview(review2, internshipId);
        int reviewId3 = reviewDAO.saveReview(review3, internshipId);
        int reviewId4 = reviewDAO.saveReview(review4, internshipId);
        int reviewId5 = reviewDAO.saveReview(review5, internshipId);
        internship.setReviews(List.of(reviewId1, reviewId2, reviewId3, reviewId4, reviewId5));

        SortRequestModel requestModel = new SortRequestModel("Highest Rating", internshipId);
        SortResponseModel responseModel = interactor.createSortResponseModel(requestModel);

        //Test that the interactor returns a success response model.
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Reviews sorted successfully", responseModel.getMessage());

        //Test that the interactor properly sorted the reviews by helpfulness.
        assertEquals(new ArrayList<Integer>(List.of(reviewId3, reviewId5, reviewId4, reviewId2, reviewId1)), responseModel.getSortedOutput());
    }

    @Test
    public void testSortNewest(){
        Review review1 = new Review("jtp1", "I hated it", 0);
        Review review2 = new Review("jtp2", "It was bad", 3);
        Review review3 = new Review("jtp3", "It was amazing", 10);
        Review review4 = new Review("jtp4", "Meh", 5);
        Review review5 = new Review("jtp5", "I survived", 7);
        review1.setDatePosted(new Date(2022, Calendar.JUNE, 24));
        review2.setDatePosted(new Date(2022, Calendar.JULY, 24));
        review3.setDatePosted(new Date(2022, Calendar.JANUARY, 24));
        review4.setDatePosted(new Date(2022, Calendar.FEBRUARY, 24));
        review5.setDatePosted(new Date(2022, Calendar.DECEMBER, 24));

        Internship internship = new Internship(1, new ArrayList<>(), "Dumb", "Jtps boss");

        int internshipId = internshipDAO.saveInternshipAndReturnId(internship);
        int reviewId1 = reviewDAO.saveReview(review1, internshipId);
        int reviewId2 = reviewDAO.saveReview(review2, internshipId);
        int reviewId3 = reviewDAO.saveReview(review3, internshipId);
        int reviewId4 = reviewDAO.saveReview(review4, internshipId);
        int reviewId5 = reviewDAO.saveReview(review5, internshipId);
        internship.setReviews(List.of(reviewId1, reviewId2, reviewId3, reviewId4, reviewId5));

        SortRequestModel requestModel = new SortRequestModel("Newest", internshipId);
        SortResponseModel responseModel = interactor.createSortResponseModel(requestModel);

        //Test that the interactor returns a success response model.
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Reviews sorted successfully", responseModel.getMessage());

        //Test that the interactor properly sorted the reviews by helpfulness.
        assertEquals(new ArrayList<Integer>(List.of(reviewId5, reviewId2, reviewId1, reviewId4, reviewId3)), responseModel.getSortedOutput());
    }
}
