package usecases.postreview;

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
import service.ServerStatus;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;
import service.dao.IUserDAO;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PostReviewTest {

    @Autowired
    private IReviewDAO reviewDAO;
    @Autowired
    private IInternshipDAO internshipDAO;
    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private PostReviewFactory postReviewFactory;

    private PostReview postReview;

    @BeforeEach
    public void init() {
        postReview = new PostReview(reviewDAO, internshipDAO, userDAO, postReviewFactory);
    }

    @Test
    @Transactional
    public void testPostReviewWithValidRequestModel() {
        Internship internship = new Internship(
                UUID.randomUUID(),
                "test",
                "leo"
        );
        Internship savedInternship = internshipDAO.save(internship);
        User user = new User();
        user.setUsername("leo");
        userDAO.save(user);
        PostReviewRequest request = new PostReviewRequest(
                "I love Apple",
                "leo",
                savedInternship.getId(),
                5
        );
        PostReviewResponse response = postReview.addReviewToInternship(request);
        assertEquals(ServerStatus.SUCCESS, response.getStatus());

        List<Review> reviews = reviewDAO.getReviewsByInternship(savedInternship.getId());
        Review savedReview = reviews.get(0);
        assertEquals("leo", savedReview.getUser().getUsername());
        assertEquals("I love Apple", savedReview.getContent());
        assertEquals(5, savedReview.getRating());
    }
}
