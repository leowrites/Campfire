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

    private CommentInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new CommentInteractor(reviewDAO, commentDAO);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments (id serial primary key, data varchar)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS reviews (id serial primary key, data varchar)");
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
        ArrayList<Integer> reviewComments = review.getComments();
        assertEquals(reviewComments.size(), 1);
        assertEquals("justinli", commentDAO.getComment(reviewComments.get(0)).getUserId());
        assertEquals("i love apple", commentDAO.getComment(reviewComments.get(0)).getContent());
    }

    @Test
    public void testLeaveCommentOnComment() {
        Comment parentComment = new Comment("stevejobs", "this internship is awesome");
        int parentCommentId = commentDAO.saveComment(parentComment);
        CommentRequestModel requestModel = new CommentRequestModel("justinli",
                "Review",
                parentCommentId,
                "i love apple");
        CommentResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Comment posted successfully.", responseModel.getMessage());
        // test that comment was properly saved in the comments table
        Comment comment = commentDAO.getComment(1);
        assertEquals("justinli", comment.getUserId());
        assertEquals("i love apple", comment.getContent());
        // test that the parent comment's comments are updated accordingly
        ArrayList<Integer> comments = parentComment.getComments();
        assertEquals(comments.size(), 1);
        assertEquals("justinli", commentDAO.getComment(comments.get(0)).getUserId());
        assertEquals("i love apple", commentDAO.getComment(comments.get(0)).getContent());
    }



}
