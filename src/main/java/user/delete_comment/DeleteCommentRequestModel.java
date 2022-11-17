package user.delete_comment;

import entity.Comment;

import java.util.ArrayList;

public class DeleteCommentRequestModel {

    private final ArrayList<Comment> comments;
    private final String commentId;

    private final String userId;

    private final int accessLevel;

    public DeleteCommentRequestModel(ArrayList<Comment> comments,
                                     String commentId,
                                     String userId,
                                     int accessLevel){
        this.comments = comments;
        this.commentId = commentId;
        this.userId = userId;
        this.accessLevel = accessLevel;
    }

    public ArrayList<Comment> getComments() {return this.comments;}
    public String getCommentId(){
        return this.commentId;
    }

    public String getUserId(){return this.userId;}

    public int getaccesslevel(){
        return this.accessLevel;
    }

}
