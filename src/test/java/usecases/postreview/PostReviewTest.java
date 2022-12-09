package usecases.postreview;

import entity.Internship;
import entity.Review;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PostReviewTest {

    @Autowired
    private IReviewDAO reviewDAO;
    @Autowired
    private IInternshipDAO internshipDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PostReviewFactory postReviewFactory;

    private PostReview postReview;

    @BeforeEach
    public void init() {
        postReview = new PostReview(reviewDAO, internshipDAO, postReviewFactory);
        jdbcTemplate.execute("DROP TABLE IF EXISTS reviews");
        jdbcTemplate.execute("DROP TABLE IF EXISTS internships");
        jdbcTemplate.execute("CREATE TABLE reviews (id serial primary key, data varchar, internshipid integer)");
        jdbcTemplate.execute("CREATE TABLE internships (id serial primary key, data varchar, companyid integer)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM reviews");
    }

    @Test
    public void testPostReviewWithValidRequestModel() {
        Internship internship = new Internship(
                0,
                new ArrayList<>(),
                "test",
                "leo"
        );
        Integer internshipId = internshipDAO.saveInternshipAndReturnId(internship);
        PostReviewRequest request = new PostReviewRequest(
                "I love Apple",
                "Leo",
                5
        );
        request.setInternshipId(internshipId.toString());
        PostReviewResponse response = postReview.addReviewToInternship(request);
        assertEquals(ServerStatus.SUCCESS, response.getStatus());
        assertEquals("You have successfully posted a review to reviewId 1", response.getMessage());

        Review review = reviewDAO.getReview(1);
        assertEquals("Leo", review.getUserId());
        assertEquals("I love Apple", review.getContent());
        assertEquals(5, review.getRating());
    }
}
