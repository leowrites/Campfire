package user.delete_comment;

import entity.Comment;
import entity.Review;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeleteCommentDataAccess implements IDeleteCommentDataAccess {

    @Override
    public Comment getComment(String commentId){
        // Connect this to Spring DB, should take the commentId and return the comment in the database
        // with the corresponding Id
        Comment comment = new Comment();
        return comment;
    }

    public ArrayList<Comment> getComments(String parentType, String parentId){
        // Connect this to Spring DB, should take the parentId (which is either
        // a Review or Comment) and return the corresponding list of comments in the database
        // with the corresponding parentId
        // Need to pass in parentType to know which table in DB to search
        ArrayList<Comment> comments = new ArrayList<Comment>();
        return comments;
    }

    public Comment getCommentParent(String parentId){
        // Call this function in getComments if parentType is a comment
        // Can also call this function in updateCommentsComment
        Comment comment = new Comment();
        return comment;
    }

    public Review getReviewParent(String parentid){
        // Call this function in getComments if parentType is a review
        // Can also call this function in updateCommentsReview
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

    public void updateCommentsReview(String parentid, ArrayList<Comment> newComments){
        // Call this function in updateComments if parentType is a review
    }

    public void updateCommentComment(String parentid, ArrayList<Comment> newComments){
        // Call this function in updateComments if parentType is a comment
    }
}
