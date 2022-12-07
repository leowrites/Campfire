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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        HelpfulRequestModel requestModel = new HelpfulRequestModel(true, reviewId, "justinli");
        HelpfulResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote received.", responseModel.getMessage());
        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numLikes = review.getNumLikes();
        assertEquals(1, numLikes);
        ArrayList<String> votedUsers = review.getVotedUsers();
        assertEquals(1, votedUsers.size());
        assertEquals("justinli", votedUsers.get(0));
    }

    @Test
    public void testVoteReviewAsUnhelpful() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel = new HelpfulRequestModel(false, reviewId, "justinli");
        HelpfulResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote received.", responseModel.getMessage());
        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numDislikes = review.getNumDislikes();
        assertEquals(1, numDislikes);
        ArrayList<String> votedUsers = review.getVotedUsers();
        assertEquals(1, votedUsers.size());
        assertEquals("justinli", votedUsers.get(0));
    }

    @Test
    public void testMultipleVotes() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);

        // create 3 thumbs up reviews
        HelpfulRequestModel requestModel1 = new HelpfulRequestModel(true, reviewId, "justinli");
        HelpfulRequestModel requestModel2 = new HelpfulRequestModel(true, reviewId, "leoliu");
        HelpfulRequestModel requestModel3 = new HelpfulRequestModel(true, reviewId, "mingikwon");
        interactor.create(requestModel1);
        interactor.create(requestModel2);
        interactor.create(requestModel3);

        //create 2 thumbs down reviews
        HelpfulRequestModel requestModel4 = new HelpfulRequestModel(false, reviewId, "timcook");
        HelpfulRequestModel requestModel5 = new HelpfulRequestModel(false, reviewId, "billgates");
        interactor.create(requestModel4);
        interactor.create(requestModel5);

        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        assertEquals(3, review.getNumLikes());
        assertEquals(2, review.getNumDislikes());
        ArrayList<String> votedUsers = review.getVotedUsers();
        assertEquals(5, votedUsers.size());
        assertEquals("justinli", votedUsers.get(0));
        assertEquals("leoliu", votedUsers.get(1));
        assertEquals("mingikwon", votedUsers.get(2));
        assertEquals("timcook", votedUsers.get(3));
        assertEquals("billgates", votedUsers.get(4));
    }

    @Test
    public void testUserTriesLeavesMultipleVotesOfSameType() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel = new HelpfulRequestModel(true, reviewId, "justinli");
        interactor.create(requestModel);
        HelpfulResponseModel responseModel = interactor.create(requestModel);
        assertNull(responseModel);
    }
}
