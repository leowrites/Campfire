package user.deletecomment;

import entity.Comment;
import entity.Review;
import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DeleteCommentInteractorTest {

    @Autowired
    private IReviewDAO reviewDAO;
    @Autowired
    private ICommentDAO commentDAO;
    @Autowired
    private IUserDAO userDAO;
    @Autowired IDeleteCommentInput interactor;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (username varchar(50) primary key, data varchar)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments (id serial primary key, data varchar)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS reviews (id serial primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("DELETE FROM reviews");
        jdbcTemplate.execute("DELETE FROM comments");
    }

    @Test
    public void testDeleteCommentWithValidRequestDeleteFromReview() {
        User user = new User("leo", "mail.com", "pass", "leo");
        userDAO.saveUser(user);
        Review review = new Review("leo", "Hello World", 5);
        Comment comment = new Comment("leo", "I like this review");
        int commentId = commentDAO.saveComment(comment);
        review.getComments().add(commentId);
        int reviewId = reviewDAO.saveReview(review);

        Review savedReview = reviewDAO.getReview(reviewId);
        Comment savedComment = commentDAO.getComment(commentId);
        assertNotNull(savedReview);
        assertNotNull(savedComment);
        assertEquals(savedReview.getComments().get(0), commentId);

        DeleteCommentResponseModel response = interactor.deleteComment(
                new DeleteCommentRequestModel(commentId, "Review", reviewId, "leo"));
        assertEquals("Comment has been successfully deleted" , response.getMessage());
        Review actualReview = reviewDAO.getReview(reviewId);
        assertEquals(actualReview.getComments().size(), 0);
        Comment actualComment = commentDAO.getComment(commentId);
        assertNull(actualComment);
    }

    @Test
    public void testDeleteCommentWithValidRequestDeleteFromComment() {
        User user = new User("leo", "mail.com", "pass", "leo");
        userDAO.saveUser(user);
        Comment parent = new Comment("leo", "I am parent comment");
        Comment child = new Comment("leo", "I am child comment");
        int childId = commentDAO.saveComment(child);
        parent.getComments().add(childId);
        int parentId = commentDAO.saveComment(parent);

        Comment savedParent = commentDAO.getComment(parentId);
        Comment savedChild = commentDAO.getComment(childId);
        assertNotNull(savedParent);
        assertNotNull(savedChild);
        assertEquals(savedParent.getComments().get(0), childId);

        DeleteCommentResponseModel response = interactor.deleteComment(new DeleteCommentRequestModel(childId,
                "Comment", parentId, "leo"));
        assertEquals("Comment has been successfully deleted" , response.getMessage());
        Comment actualParent = commentDAO.getComment(parentId);
        assertEquals(actualParent.getComments().size(), 0);
        Comment actualChild = commentDAO.getComment(childId);
        assertNull(actualChild);
    }

    @Test
    public void testDeleteCommentWithNotFoundComment() {
        User user = new User("leo", "mail.com", "pass", "leo");
        userDAO.saveUser(user);
        DeleteCommentResponseModel response = interactor.deleteComment(new DeleteCommentRequestModel(834678398,
                "Comment", 592348, "leo"));
        assertEquals("Comment not found" , response.getMessage());
    }

    @Test
    public void testDeleteCommentNotAuthorized() {
        User user = new User("leo", "mail.com", "pass", "leo");
        userDAO.saveUser(user);
        Comment parent = new Comment("definitely not leo", "I am parent comment");
        Comment child = new Comment("definite not leo", "I am child comment");
        int childId = commentDAO.saveComment(child);
        parent.getComments().add(childId);
        int parentId = commentDAO.saveComment(parent);
        DeleteCommentResponseModel response = interactor.deleteComment(new DeleteCommentRequestModel(childId,
                "Comment", parentId, "leo"));
        assertEquals("Not authorized!" , response.getMessage());
    }
}
