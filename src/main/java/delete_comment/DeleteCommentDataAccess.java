package delete_comment;

import entity.Comment;
import user.comment.ICommentDataAccess;

public class DeleteCommentDataAccess implements IDeleteCommentDataAccess {

    @Override
    public Comment getComment(String commentId){
        // Connect this to Spring DB, should take the commentId and return the comment in the database
        // with the corresponding Id
        Comment comment = new Comment();
        return comment;
    }

    public void removeComment(String commentId){
        // Connect this to Spring DB, should take the commentId and remove the comment from the database
        // Does not return anything
    }
}
