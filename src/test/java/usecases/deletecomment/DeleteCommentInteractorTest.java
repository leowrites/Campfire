package usecases.deletecomment;

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
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import usecases.exceptions.CommentNotFoundException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @Transactional
    public void testDeleteCommentWithValidRequestDeleteFromReview() {
        User user = new User("leo", "mail.com", "pass", "leo");
        userDAO.saveUser(user);
        Review review = new Review("Hello World", 5);
        Comment comment = new Comment("I like this review");

        comment.setUser(user);
        Comment savedComment = commentDAO.save(comment);
        review.setUser(user);
        review.getComments().add(savedComment);
        Review savedReview = reviewDAO.save(review);


        assertNotNull(savedReview);
        assertNotNull(savedComment);
        assertThat(savedReview.getComments().get(0))
                .usingRecursiveComparison()
                .isEqualTo(savedComment);

        DeleteCommentResponseModel response = interactor.deleteComment(
                new DeleteCommentRequestModel(savedComment.getId(), "Review", savedReview.getId(), "leo"));

        assertEquals("Comment has been successfully deleted" , response.getMessage());
        Review actualReview = reviewDAO.getReview(savedReview.getId());
        assertEquals(actualReview.getComments().size(), 0);
        assertThrows(CommentNotFoundException.class, () ->
                commentDAO.getComment(savedComment.getId()));
    }

    @Test
    @Transactional
    public void testDeleteCommentWithValidRequestDeleteFromComment() {
        User user = new User("leo", "mail.com", "pass", "leo");
        userDAO.saveUser(user);

        Comment parent = new Comment("I am parent comment");
        parent.setUser(user);
        Comment child = new Comment("I am child comment");
        child.setUser(user);

        Comment savedChild = commentDAO.save(child);
        parent.getComments().add(savedChild);

        Comment savedParent = commentDAO.save(parent);

        assertNotNull(savedParent);
        assertNotNull(savedChild);
        assertThat(savedParent.getComments().get(0))
                .usingRecursiveComparison()
                .isEqualTo(child);

        DeleteCommentResponseModel response = interactor.deleteComment(new DeleteCommentRequestModel(savedChild.getId(),
                "Comment", savedParent.getId(), user.getUsername()));
        assertEquals("Comment has been successfully deleted" , response.getMessage());
    }

    @Test
    public void testDeleteCommentWithNotFoundComment() {
        User user = new User("leo", "mail.com", "pass", "leo");
        userDAO.saveUser(user);
        DeleteCommentResponseModel response = interactor.deleteComment(new DeleteCommentRequestModel(UUID.randomUUID(),
                "Comment", UUID.randomUUID(), "leo"));
        assertEquals("No Comment Found!" , response.getMessage());
    }

    @Test
    public void testDeleteCommentNotAuthorized() {
        User user1 = new User();
        user1.setUsername("leo");
        User user2 = new User();
        user2.setUsername("not leo");
        userDAO.save(user1);
        userDAO.save(user2);

        Comment parent = new Comment("I am parent comment");
        Comment child = new Comment("I am child comment");
        parent.setUser(user1);
        child.setUser(user1);

        Comment savedChild = commentDAO.save(child);
        parent.getComments().add(savedChild);
        Comment savedParent = commentDAO.save(parent);

        DeleteCommentResponseModel response = interactor.deleteComment(new DeleteCommentRequestModel(savedChild.getId(),
                "Comment", savedParent.getId(), user2.getUsername()));
        assertEquals("Not authorized!" , response.getMessage());
    }
}
