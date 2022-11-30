package user.deletecomment;

import entity.Comment;
import entity.Review;

import java.util.ArrayList;

public interface IDeleteCommentDataAccess {

    ArrayList<Comment> getComments(String parentType, String parentId);

    ArrayList<Comment> getCommentsComment(String parentId);

    ArrayList<Comment> getCommentsReview(String parentId);

    Comment getComment(String Id);

    Review getReview(String Id);

    void updateComments(String parentType, String parentId, ArrayList<Comment> newComments);

    void updateReview(String Id, ArrayList<Comment> newComments);

    void updateComment(String Id, ArrayList<Comment> newComments);

}
