package usecases.comment;

import entity.Comment;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.dao.ICommentDAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentDAOTest {

    @Autowired
    private ICommentDAO commentDAO;

    @Test
    public void testCommentGetsByIdAndReturnsCommentObject() {
        Comment inputComment = new Comment("i love apple");
        Comment savedComment = commentDAO.save(inputComment);
        assertNotNull(savedComment);

        assertEquals("i love apple", savedComment.getContent());
    }

    @Test
    @Transactional
    public void testAddCommentToComment() {
        Comment parentComment = new Comment("i love apple");
        Comment savedParentComment = commentDAO.save(parentComment);
        assertNotNull(savedParentComment);

        Comment childComment = new Comment("i hate apple");
        Comment savedChildComment = commentDAO.save(childComment);
        assertNotNull(savedChildComment);

        savedParentComment.getComments().add(savedChildComment);
        savedParentComment = commentDAO.save(savedParentComment);

        assertThat(savedParentComment.getComments().get(0))
                .usingRecursiveComparison()
                .isEqualTo(savedChildComment);
    }

}
