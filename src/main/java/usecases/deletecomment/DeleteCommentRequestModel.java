package usecases.deletecomment;

/** A request model for the deletecomment use case that frames the input data into an object. Holds
 * an int representation of the comment's id in commentId, a String of the type of the parent to the
 * comment in parentType, an int representation of the parent's id in parentId, and a String
 * representation of the id of the user who made the comment in userId.
 */
public class DeleteCommentRequestModel {

    private final int commentId;

    private final String parentType;

    private final int parentId;

    private String userId;

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

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
