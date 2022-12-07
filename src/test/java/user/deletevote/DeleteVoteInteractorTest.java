package user.deletevote;

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
import user.votehelpful.HelpfulRequestModel;
import user.votehelpful.IHelpfulInputBoundary;
import user.votehelpful.VoteDecision;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DeleteVoteInteractorTest {

    @Autowired
    private IReviewDAO reviewDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IHelpfulInputBoundary helpfulInteractor;

    private DeleteVoteInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new DeleteVoteInteractor(reviewDAO);
        jdbcTemplate.execute("DROP TABLE IF EXISTS reviews");
        jdbcTemplate.execute("CREATE TABLE reviews (id serial primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM reviews");
    }

    @Test
    public void testUserLeavesAndRemovesHelpfulVote() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);

        HelpfulRequestModel requestModel1 = new HelpfulRequestModel("Helpful", reviewId, "justinli");
        HelpfulRequestModel requestModel2 = new HelpfulRequestModel("Helpful", reviewId, "leoliu");
        helpfulInteractor.create(requestModel1);
        helpfulInteractor.create(requestModel2);

        DeleteVoteRequestModel requestModel = new DeleteVoteRequestModel(reviewId, "justinli");
        DeleteVoteResponseModel responseModel = interactor.create(requestModel);

        // test that interactor returns a successful response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote removed.", responseModel.getMessage());

        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numLikes = review.getNumLikes();
        assertEquals(1, numLikes);
        HashMap<String, VoteDecision> votedUsers = review.getVotedUsers();
        assertEquals(1, votedUsers.size());
        assertEquals(true, votedUsers.keySet().contains("leoliu"));
    }

    @Test
    public void testUserLeavesAndRemovesUnhelpfulVote() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);

        HelpfulRequestModel requestModel1 = new HelpfulRequestModel("Unhelpful", reviewId, "justinli");
        HelpfulRequestModel requestModel2 = new HelpfulRequestModel("Unhelpful", reviewId, "leoliu");
        helpfulInteractor.create(requestModel1);
        helpfulInteractor.create(requestModel2);

        DeleteVoteRequestModel requestModel = new DeleteVoteRequestModel(reviewId, "justinli");
        DeleteVoteResponseModel responseModel = interactor.create(requestModel);

        // test that interactor returns a successful response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Vote removed.", responseModel.getMessage());

        // test that the review was properly updated
        review = reviewDAO.getReview(reviewId);
        int numDislikes = review.getNumDislikes();
        assertEquals(1, numDislikes);
        HashMap<String, VoteDecision> votedUsers = review.getVotedUsers();
        assertEquals(1, votedUsers.size());
        assertEquals(true, votedUsers.keySet().contains("leoliu"));
    }

    @Test
    public void testUserTriesRemovingNonexistentVote() {
        Review review = new Review("stevejobs", "apple is the best!", 10);
        int reviewId = reviewDAO.saveReview(review);
        DeleteVoteRequestModel requestModel = new DeleteVoteRequestModel(reviewId, "justinli");
        DeleteVoteResponseModel responseModel = interactor.create(requestModel);
        assertNull(responseModel);
    }
}
