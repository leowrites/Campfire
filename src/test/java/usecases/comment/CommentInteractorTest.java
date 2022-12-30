package usecases.comment;

import entity.Comment;
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
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentInteractorTest {

    @Autowired
    private ICommentDAO commentDAO;

    @Autowired
    private IReviewDAO reviewDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private CommentFactory commentFactory;

    private CommentInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new CommentInteractor(reviewDAO, commentDAO, userDAO, commentFactory);
    }

    @Test
    @Transactional
    public void testLeaveCommentOnReview() {
        User user = new User();
        user.setUsername("justinli");
        userDAO.save(user);
        Review review = new Review("this internship is awesome", 10);
        review.setUser(user);
        Review savedReview = reviewDAO.save(review);

        CommentRequestModel requestModel = new CommentRequestModel(
                "Review",
                savedReview.getId(),
                "i love apple");
        requestModel.setUsername(user.getUsername());
        CommentResponseModel responseModel = interactor.create(requestModel);

        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Comment posted successfully.", responseModel.getMessage());
        // test that comment was properly saved in the comments table
        Comment comment = commentDAO.getComment(responseModel.getId());
        assertEquals("justinli", comment.getUser().getUsername());
        assertEquals("i love apple", comment.getContent());

        // test that the review's comments are updated accordingly
        review = reviewDAO.getReview(review.getId());

        List<Comment> reviewComments = review.getComments();
        assertEquals(1, review.getComments().size());

        assertEquals("justinli", commentDAO.getComment(reviewComments.get(0).getId()).getUser().getUsername());
        assertEquals("i love apple", commentDAO.getComment(reviewComments.get(0).getId()).getContent());
    }

    @Test
    @Transactional
    public void testLeaveCommentOnComment() {
        User user = new User();
        user.setUsername("justinli");
        userDAO.save(user);
        Comment parentComment = new Comment("this internship is awesome");
        parentComment.setUser(user);
        Comment savedParentComment = commentDAO.save(parentComment);

        CommentRequestModel requestModel = new CommentRequestModel(
                "Comment",
                savedParentComment.getId(),
                "i love apple");
        requestModel.setUsername(user.getUsername());
        CommentResponseModel responseModel = interactor.create(requestModel);

        // test that the interactor returns a success response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Comment posted successfully.", responseModel.getMessage());

        // test that comment was properly saved in the comments table
        Comment comment = commentDAO.getComment(responseModel.getId());
        assertEquals("justinli", comment.getUser().getUsername());
        assertEquals("i love apple", comment.getContent());

        // test that the parent comment's comments are updated accordingly
        parentComment = commentDAO.getComment(parentComment.getId());
        List<Comment> parentCommentChildren = parentComment.getComments();

        assertEquals(1, parentCommentChildren.size());
        assertEquals("justinli", commentDAO.getComment(parentCommentChildren.get(0).getId()).getUser().getUsername());
        assertEquals("i love apple", commentDAO.getComment(parentCommentChildren.get(0).getId()).getContent());
    }

    @Test
    public void testCannotFindParent() {
        CommentRequestModel requestModel = new CommentRequestModel(
                "Review",
                UUID.randomUUID(),
                "i love apple");
        requestModel.setUsername("justinli");
        CommentResponseModel responseModel = interactor.create(requestModel);
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("Review does not exist.", responseModel.getMessage());
    }

    @Test
    public void testInvalidParentTypeGiven() {
        CommentRequestModel requestModel = new CommentRequestModel(
                "Internship",
                UUID.randomUUID(),
                "i love apple");
        requestModel.setUsername("justinli");
        CommentResponseModel responseModel = interactor.create(requestModel);
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("Invalid parent type.", responseModel.getMessage());
    }

}
