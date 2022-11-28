package user.deletecomment;

import entity.Comment;
import entity.Review;

import java.util.ArrayList;

public interface IDeleteCommentDataAccess {

    public ArrayList<Comment> getComments(String parentType, String parentId);

    public ArrayList<Comment> getCommentsComment(String parentId);

    public ArrayList<Comment> getCommentsReview(String parentId);

    public Comment getComment(String Id);

    public Review getReview(String Id);

    public void updateComments(String parentType, String parentId, ArrayList<Comment> newComments);

    public void updateReview(String Id, ArrayList<Comment> newComments);

    public void updateComment(String Id, ArrayList<Comment> newComments);

}
