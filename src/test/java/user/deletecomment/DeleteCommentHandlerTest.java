package user.deletecomment;

import entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class DeleteCommentHandlerTest {

    ArrayList<Comment> commentsNew;
    ArrayList<Comment> comments;
    ArrayList<Comment> commentsEmpty;

    String commentId;

    Comment comment1;

    Comment comment2;

    Comment comment3;

    Comment comment4;

    @BeforeEach
    public void setup(){
        commentsEmpty = new ArrayList<Comment>();
        comment1 = new Comment("commentId1", "userId1", "This is a test 1", commentsEmpty);
        comment2 = new Comment("commentId2", "userId2", "This is a test 2", commentsEmpty);
        comment3 = new Comment("commentId3", "userId3", "This is a test 3", commentsEmpty);
        comment4 = new Comment("commentId4", "userId4", "This is a test 4", commentsEmpty);
        comments = new ArrayList<Comment>();
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
    }

    @Test
    void testCheckCommentDeletedExists(){
        commentId = "commentId1";
        DeleteCommentHandler deleteCommentHandlerTest = new DeleteCommentHandler(commentId, comments);
        deleteCommentHandlerTest.deleteComment();
        commentsNew = new ArrayList<>();
        commentsNew.add(comment2);
        commentsNew.add(comment3);
        commentsNew.add(comment4);
        assertEquals(deleteCommentHandlerTest.getComments(), commentsNew);
    }
}
