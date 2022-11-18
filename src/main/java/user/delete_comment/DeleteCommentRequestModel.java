package user.delete_comment;

import entity.Comment;

import java.util.ArrayList;

public class DeleteCommentRequestModel {

    private final String parentId;

    private final String commentId;

    private final String userId;

    private final int accessLevel;

    public DeleteCommentRequestModel(String parentId,
                                     String commentId,
                                     String userId,
                                     int accessLevel){
        this.parentId = parentId;
        this.commentId = commentId;
        this.userId = userId;
        this.accessLevel = accessLevel;
    }

    public String getParentId() {return this.parentId;}
    public String getCommentId(){
        return this.commentId;
    }

    public String getUserId(){return this.userId;}

    public int getaccesslevel(){
        return this.accessLevel;
    }

}
