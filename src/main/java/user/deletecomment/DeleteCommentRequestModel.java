package user.deletecomment;

public class DeleteCommentRequestModel {

    private final String parentType;

    private final String parentId;

    private final String commentId;

    private final String userId;

    private final int accessLevel;

    public DeleteCommentRequestModel(String parentType,
                                     String parentId,
                                     String commentId,
                                     String userId,
                                     int accessLevel){
        this.parentType = parentType;
        this.parentId = parentId;
        this.commentId = commentId;
        this.userId = userId;
        this.accessLevel = accessLevel;
    }
    // parentType is one of two strings: "Comment" or "Review"
    public String getParentType() {return this.parentType;}

    public String getParentId() {return this.parentId;}

    public String getCommentId(){
        return this.commentId;
    }

    public String getUserId(){return this.userId;}

    public int getAccessLevel(){
        return this.accessLevel;
    }

}
