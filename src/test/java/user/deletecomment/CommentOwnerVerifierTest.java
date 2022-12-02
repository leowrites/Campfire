package user.deletecomment;

import entity.Comment;
import exceptions.NotOwnCommentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class CommentOwnerVerifierTest {

    ArrayList<Integer> commentsEmpty;
    Comment commentTest;

    String userIdTest;

    @BeforeEach
    public void setup(){
        commentsEmpty = new ArrayList<>();
        commentTest = new Comment("userId1", "This is a test");
    }
    @Test
    public void testOwnerVerifierNotMatch(){
        userIdTest = "userId2";
        OwnerVerifierComment ownerVerifierCommentTest = new OwnerVerifierComment(commentTest, userIdTest);
        Throwable exception = assertThrows(NotOwnCommentException.class, ownerVerifierCommentTest::verify);
        assertEquals("Comment does not belong to user", exception.getMessage());
    }
}
