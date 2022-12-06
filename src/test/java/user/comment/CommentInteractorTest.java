package user.comment;

import entity.Comment;
import entity.Review;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.ServerStatus;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentInteractorTest {

    @Autowired
    private ICommentDAO commentDAO;

    @Autowired
    private IReviewDAO reviewDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommentFactory commentFactory;

    private CommentInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new CommentInteractor(reviewDAO, commentDAO, commentFactory);
        jdbcTemplate.execute("DROP TABLE IF EXISTS reviews");
        jdbcTemplate.execute("DROP TABLE IF EXISTS comments");
        jdbcTemplate.execute("CREATE TABLE comments (id serial primary key, data varchar, parentid integer)");
        jdbcTemplate.execute("CREATE TABLE reviews (id serial primary key, data varchar, internshipid integer)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM comments");
        jdbcTemplate.execute("DELETE FROM reviews");
    }

    @Test
    public void testLeaveCommentOnReview() {
        Review review = new Review("stevejobs", "this intership is awesome", 10);
        int reviewId = reviewDAO.saveReview(review);
        CommentRequestModel requestModel = new CommentRequestModel("justinli",
                "Review",
                reviewId,
                "i love apple");
        CommentResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Comment posted successfully.", responseModel.getMessage());
        // test that comment was properly saved in the comments table
        Comment comment = commentDAO.getComment(1);
        assertEquals("justinli", comment.getUserId());
        assertEquals("i love apple", comment.getContent());
        // test that the review's comments are updated accordingly
        review = reviewDAO.getReview(reviewId);
        ArrayList<Integer> reviewComments = review.getComments();
        assertEquals(1, reviewComments.size());
        assertEquals("justinli", commentDAO.getComment(reviewComments.get(0)).getUserId());
        assertEquals("i love apple", commentDAO.getComment(reviewComments.get(0)).getContent());
    }

    @Test
    public void testLeaveCommentOnComment() {
        Comment parentComment = new Comment("stevejobs", "this internship is awesome");
        int parentCommentId = commentDAO.saveComment(parentComment);
        CommentRequestModel requestModel = new CommentRequestModel("justinli",
                "Comment",
                parentCommentId,
                "i love apple");
        CommentResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Comment posted successfully.", responseModel.getMessage());
        // test that comment was properly saved in the comments table
        Comment comment = commentDAO.getComment(2);
        assertEquals("justinli", comment.getUserId());
        assertEquals("i love apple", comment.getContent());
        // test that the parent comment's comments are updated accordingly
        parentComment = commentDAO.getComment(parentCommentId);
        ArrayList<Integer> comments = parentComment.getComments();
        assertEquals(1, comments.size());
        assertEquals("justinli", commentDAO.getComment(comments.get(0)).getUserId());
        assertEquals("i love apple", commentDAO.getComment(comments.get(0)).getContent());
    }

    @Test
    public void testCannotFindParent() {
        CommentRequestModel requestModel = new CommentRequestModel("justinli",
                "Review",
                1,
                "i love apple");
        CommentResponseModel responseModel = interactor.create(requestModel);
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("Review does not exist.", responseModel.getMessage());
    }

    @Test
    public void testInvalidParentTypeGiven() {
        CommentRequestModel requestModel = new CommentRequestModel("justinli",
                "Internship",
                1,
                "i love apple");
        CommentResponseModel responseModel = interactor.create(requestModel);
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("Invalid parent type.", responseModel.getMessage());
    }

}
