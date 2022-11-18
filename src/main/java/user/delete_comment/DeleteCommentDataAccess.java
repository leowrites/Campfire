package user.delete_comment;

import entity.Comment;

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

    @Override
    public void updateComments(String parentType, String parentId, ArrayList<Comment> newComments){
        // Connect this to Spring DB, should take the parentId (which is either a Review or Comment)
        // and set the comments to the new comments, which has one comment deleted
        // Does not return anything
        // Need to pass in parentType to know which table in DB to search
    }
}
