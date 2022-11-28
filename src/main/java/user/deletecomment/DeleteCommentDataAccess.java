package user.deletecomment;

import entity.Comment;
import entity.Review;

import java.util.ArrayList;

public class DeleteCommentDataAccess implements IDeleteCommentDataAccess {

    @Override
    public ArrayList<Comment> getComments(String parentType, String parentId){
        // Connect this to Spring DB, should take the parentId (which is either
        // a Review or Comment) and return the corresponding list of comments in the database
        // with the corresponding parentId
        // Need to pass in parentType to know which table in DB to search
        ArrayList<Comment> comments = new ArrayList<Comment>();
        return comments;
    }

    @Override
    public ArrayList<Comment> getCommentsComment(String parentId){
        // Connect this to Spring DB, should take the parentId, and return the corresponding list of comments
        // Call this in getComments if parentType is a comment
        ArrayList<Comment> comments = new ArrayList<Comment>();
        return comments;
    }

    @Override
    public ArrayList<Comment> getCommentsReview(String parentId){
        // Connect this to Spring DB, should take the parentId, and return the corresponding list of comments
        // Call this in getComments if parentType is a review
        ArrayList<Comment> comments = new ArrayList<Comment>();
        return comments;
    }

    @Override
    public Comment getComment(String Id){
        // Connect this to Spring DB, should take the Id and return the comment in the database
        // with the corresponding Id
        Comment comment = new Comment();
        return comment;
    }

    @Override
    public Review getReview(String Id){
        // Connect this to Spring DB, should take the Id and return the Review in the database
        // with the corresponding Id
        Review review = new Review();
        return review;
    }

    @Override
    public void updateComments(String parentType, String parentId, ArrayList<Comment> newComments){
        // Connect this to Spring DB, should take the parentId (which is either a Review or Comment)
        // and set the comments to the new comments, which has one comment deleted
        // Does not return anything
        // Need to pass in parentType to know which table in DB to search
    }

    @Override
    public void updateReview(String Id, ArrayList<Comment> newComments){
        // Call this function in updateComments if parentType is a review
    }

    @Override
    public void updateComment(String Id, ArrayList<Comment> newComments){
        // Call this function in updateComments if parentType is a comment
    }
}
