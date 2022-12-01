package user.deletecomment;

public class DeleteCommentRequestModel {

    private final String commentId;

    private final String parentType;

    private final String parentId;

    private final String userId;

    private final int accessLevel;

    public DeleteCommentRequestModel(String commentId,
                                     String parentType,
                                     String parentId,
                                     String userId,
                                     int accessLevel){
        this.commentId = commentId;
        this.parentType = parentType;
        this.parentId = parentId;
        this.userId = userId;
        this.accessLevel = accessLevel;
    }
    // parentType is one of two strings: "Comment" or "Review"
    public String getCommentId(){ return this.commentId;}

    public String getParentType() {return this.parentType;}

    public String getParentId() {return this.parentId;}

    public String getUserId(){return this.userId;}

    public int getAccessLevel(){
        return this.accessLevel;
    }

}
