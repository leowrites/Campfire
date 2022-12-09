package usecases.votehelpful;

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

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testUserWithNoPriorVoteVotesReviewAsHelpful() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel = new HelpfulRequestModel("Helpful", reviewId, "justinli");
        HelpfulResponseModel responseModel = interactor.create(requestModel);

        // test that the interactor returns a successful response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote received.", responseModel.getMessage());
        assertEquals(VoteDecision.HELPFUL, responseModel.getVote());

        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numLikes = review.getNumLikes();
        assertEquals(1, numLikes);
        assertEquals(0, review.getNumDislikes());
        HashMap<String, VoteDecision> votedUsers = review.getVotedUsers();
        assertEquals(1, votedUsers.keySet().size());
        assertTrue(votedUsers.containsKey("justinli"));
        assertEquals(VoteDecision.HELPFUL, votedUsers.get("justinli"));
    }



    @Test
    public void testUserWithNoPriorVotesVotesReviewAsUnhelpful() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel = new HelpfulRequestModel("Unhelpful", reviewId, "justinli");
        HelpfulResponseModel responseModel = interactor.create(requestModel);

        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote received.", responseModel.getMessage());
        assertEquals(VoteDecision.UNHELPFUL, responseModel.getVote());

        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numDislikes = review.getNumDislikes();
        assertEquals(1, numDislikes);
        HashMap<String, VoteDecision> votedUsers = review.getVotedUsers();
        assertEquals(1, votedUsers.keySet().size());
        assertTrue(votedUsers.containsKey("justinli"));
        assertEquals(VoteDecision.UNHELPFUL, votedUsers.get("justinli"));
    }

    @Test
    public void testUserVotesTwiceForHelpful() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel1 = new HelpfulRequestModel("Helpful", reviewId, "justinli");
        HelpfulRequestModel requestModel2 = new HelpfulRequestModel("Helpful", reviewId, "justinli");
        interactor.create(requestModel1);
        HelpfulResponseModel responseModel = interactor.create(requestModel2);

        assertEquals(VoteDecision.NONE, responseModel.getVote());
        review = reviewDAO.getReview(reviewId);
        assertEquals(0, review.getNumLikes());
        assertEquals(0, review.getVotedUsers().keySet().size());
    }

    @Test
    public void testUserVotesTwiceForUnhelpful() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel1 = new HelpfulRequestModel("Unhelpful", reviewId, "justinli");
        HelpfulRequestModel requestModel2 = new HelpfulRequestModel("Unhelpful", reviewId, "justinli");
        interactor.create(requestModel1);
        HelpfulResponseModel responseModel = interactor.create(requestModel2);

        assertEquals(VoteDecision.NONE, responseModel.getVote());
        review = reviewDAO.getReview(reviewId);
        assertEquals(0, review.getNumDislikes());
        assertEquals(0, review.getVotedUsers().keySet().size());
    }

    @Test
    public void testUserSwitchesVote() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        HelpfulRequestModel requestModel1 = new HelpfulRequestModel("Helpful", reviewId, "justinli");
        HelpfulRequestModel requestModel2 = new HelpfulRequestModel("Unhelpful", reviewId, "justinli");
        interactor.create(requestModel1);
        review = reviewDAO.getReview(reviewId);
        assertEquals(1, review.getNumLikes());
        assertEquals(0, review.getNumDislikes());
        interactor.create(requestModel2);

        review = reviewDAO.getReview(reviewId);
        assertEquals(0, review.getNumLikes());
        assertEquals(1, review.getNumDislikes());
        assertEquals(VoteDecision.UNHELPFUL, review.getVotedUsers().get("justinli"));
    }

}
