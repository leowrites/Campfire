package user.comment;

import entity.Comment;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.dao.ICommentDAO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentDAOTest {

    @Autowired
    private ICommentDAO commentDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments (id serial primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM comments");
    }

    @Test
    public void testCommentSavesAndReturnsId() {
        Comment inputComment = new Comment("justinli", "i love apple");
        int commentId = commentDAO.saveComment(inputComment);
        assertNotEquals(0, commentId);
    }

    @Test
    public void testCommentGetsByIdAndReturnsCommentObject() {
        Comment inputComment = new Comment("justinli", "i love apple");
        int commentId = commentDAO.saveComment(inputComment);
        assertNotEquals(0, commentId);
        Comment outputComment = commentDAO.getComment(commentId);
        assertNotNull(outputComment);
        assertEquals("justinli", outputComment.getUserId());
        assertEquals("i love apple", outputComment.getContent());
    }

    @Test
    public void testAddCommentToComment() {
        Comment inputComment = new Comment("justinli", "i love apple");
        int inputCommentId = commentDAO.saveComment(inputComment);
        assertNotEquals(0, inputCommentId);
        Comment commentToAdd = new Comment("stevejobs", "i hate apple");
        int commentToAddId = commentDAO.saveComment(commentToAdd);
        assertNotEquals(0, commentToAddId);
        ArrayList<Integer> comments = inputComment.getComments();
        comments.add(commentToAddId);
        inputComment.setComments(comments);
        commentDAO.updateComment(inputComment, inputCommentId);
        Comment outputComment = commentDAO.getComment(inputCommentId);
        ArrayList<Integer> outputCommentComments = outputComment.getComments();
        assertEquals(1, outputCommentComments.size());
        assertEquals(commentDAO.getComment(outputCommentComments.get(0)).getUserId(), "stevejobs");
        assertEquals(commentDAO.getComment(outputCommentComments.get(0)).getContent(), "i hate apple");
    }

}
