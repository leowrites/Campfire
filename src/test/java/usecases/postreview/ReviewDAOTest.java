package usecases.postreview;

import entity.Review;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IReviewDAO;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReviewDAOTest {

    @Autowired
    private IReviewDAO reviewDAO;

    @Test
    public void testReviewSavesAndReturnsId() {
        Review inputReview = new Review("test", 1);
        Review savedReview = reviewDAO.save(inputReview);
        assertEquals("test", savedReview.getContent());
    }

    @Test
    @Transactional
    public void testReviewGetsByIdAndReturnsReviewObject() {
        Review inputReview = new Review("apple", 1);
        Review savedReview = reviewDAO.save(inputReview);
        Review retrievedReview = reviewDAO.getReview(savedReview.getId());
        assertThat(savedReview)
                .usingRecursiveComparison()
                .isEqualTo(retrievedReview);
    }
}
