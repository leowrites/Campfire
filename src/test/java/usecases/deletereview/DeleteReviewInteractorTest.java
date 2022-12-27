package usecases.deletereview;

import entity.Internship;
import entity.Review;
import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IInternshipDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DeleteReviewInteractorTest {

    @Autowired
    private IReviewDAO reviewDAO;
    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IInternshipDAO internshipDAO;

    private UUID internshipId;
    private UUID reviewId;
    private String username;
    private IDeleteReviewInput reviewDeleter;

    @BeforeEach
    public void init() {
        User user = new User("leo", "mail.com", "pass", "leo");
        user.setAccessLevel(0);
        username = userDAO.save(user).getUsername();

        Review review = new Review("Hello World", 5);
        review.setUser(user);
        Review savedReview = reviewDAO.save(review);
        reviewId = savedReview.getId();

        Internship internship = new Internship();
        internship.setReviews(List.of(savedReview));
        internshipId = internshipDAO.save(internship).getId();

        reviewDeleter = new DeleteReviewInteractor(
                reviewDAO, internshipDAO, userDAO
        );
    }

    @Test
    @Transactional
    public void testDeleteReviewWithValidReview() {
        assertNotNull(reviewDAO.getReview(reviewId));

        DeleteReviewRequestModel request = new DeleteReviewRequestModel(internshipId, reviewId, username);

        DeleteReviewResponseModel response = reviewDeleter.deleteReview(request);

        assertEquals("Review has successfully been deleted", response.getMessage());

        Internship internshipAfterDeleteReview = internshipDAO.getInternship(internshipId);
        assertEquals(0, internshipAfterDeleteReview.getReviews().size());
    }

    @Test
    @Transactional
    public void testDeleteReviewWithUnauthorizedUser() {
        User user = new User();
        user.setUsername("not leo");

        Review review = reviewDAO.getReview(reviewId);
        review.setUser(user);

        reviewDAO.save(review);

        assertNotNull(reviewDAO.getReview(reviewId));

        DeleteReviewRequestModel request = new DeleteReviewRequestModel(internshipId, reviewId, username);

        DeleteReviewResponseModel response = reviewDeleter.deleteReview(request);

        assertEquals("Not authorized!", response.getMessage());

        Internship internshipAfterDeleteReview = internshipDAO.getInternship(internshipId);
        assertEquals(1, internshipAfterDeleteReview.getReviews().size());
    }
}
