package user.deletecomment;

public class DeleteCommentRequestModel {

    private final int commentId;

    private final String parentType;

    private final int parentId;

    private final String userId;

    public DeleteCommentRequestModel(int commentId,
                                     String parentType,
                                     int parentId,
                                     String userId){
        this.commentId = commentId;
        this.parentType = parentType;
        this.parentId = parentId;
        this.userId = userId;
    }
    // parentType is one of two strings: "Comment" or "Review"
    public int getCommentId(){ return this.commentId;}

    public String getParentType() {return this.parentType;}

    public int getParentId() {return this.parentId;}

    public String getUserId(){return this.userId;}

}
