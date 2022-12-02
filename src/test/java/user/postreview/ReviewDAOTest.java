package user.postreview;

import entity.Review;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.dao.IReviewDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReviewDAOTest {

    @Autowired
    private IReviewDAO reviewDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS reviews (id serial primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM reviews");
    }

    @Test
    public void testReviewSavesAndReturnsId() {
        Review inputReview = new Review(
                "leo",
                "test",
                1
        );
        String reviewId = reviewDAO.saveReview(inputReview);
        assertNotNull(reviewId);
    }

    @Test
    public void testReviewGetsByIdAndReturnsReviewObject() {
        Review inputReview = new Review(
                "leo",
                "apple",
                1
        );
        String reviewId = reviewDAO.saveReview(inputReview);
        assertNotNull(reviewId);
        Review outputReview = reviewDAO.getReview(reviewId);
        assertEquals("leo", outputReview.getUserId());
        assertEquals("apple", outputReview.getContent());
        assertEquals(1, outputReview.getRating());
    }
}
