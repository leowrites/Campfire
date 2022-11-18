package user.delete_comment;

import entity.Comment;

import java.util.ArrayList;

public interface IDeleteCommentDataAccess {

    Comment getComment(String commentId);

    ArrayList<Comment> getComments(String parentType, String parentId);

    void updateComments (String parentType, String parentId, ArrayList<Comment> newComments);
}
