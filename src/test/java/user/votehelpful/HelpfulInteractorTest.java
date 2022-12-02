package user.votehelpful;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class HelpfulInteractorTest {

    @Autowired
    private IReviewDAO reviewDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private HelpfulInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new HelpfulInteractor(reviewDAO);
        jdbcTemplate.execute("DROP TABLE IF EXISTS reviews");
        jdbcTemplate.execute("CREATE TABLE reviews (id serial primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM reviews");
    }

    @Test
    public void testVoteReviewAsHelpful() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel = new HelpfulRequestModel(true, reviewId);
        HelpfulResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote received.", responseModel.getMessage());
        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numLikes = review.getNumLikes();
        assertEquals(1, numLikes);
    }

    @Test
    public void testVoteReviewAsUnhelpful() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel = new HelpfulRequestModel(false, reviewId);
        HelpfulResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote received.", responseModel.getMessage());
        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numDislikes = review.getNumDislikes();
        assertEquals(1, numDislikes);
    }

    @Test
    public void testMultipleVotes() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel = new HelpfulRequestModel(true, reviewId);
        for (int i = 0; i < 5; i++) {
            HelpfulResponseModel responseModel = interactor.create(requestModel);
            // test that the interactor returns a success response model
            assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
            assertEquals("Vote received.", responseModel.getMessage());
        }
        requestModel = new HelpfulRequestModel(false, reviewId);
        for (int i = 0; i < 10; i++) {
            HelpfulResponseModel responseModel = interactor.create(requestModel);
            // test that the interactor returns a success response model
            assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
            assertEquals("Vote received.", responseModel.getMessage());
        }
        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        assertEquals(5, review.getNumLikes());
        assertEquals(10, review.getNumDislikes());
    }
}
