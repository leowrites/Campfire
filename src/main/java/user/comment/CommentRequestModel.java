package user.comment;

public class CommentRequestModel {
    private final String userId;
    private final String parentType;
    private final int parentId;
    private final String content;

    public CommentRequestModel(String userId, String parentType, int parentId, String content) {
        this.userId = userId;
        this.parentType = parentType;
        this.parentId = parentId;
        this.content = content;
    }
    public String getUserId() {
        return this.userId;
    }

    public String getParentType() {
        return this.parentType;
    }
    public int getParentId() {
        return this.parentId;
    }

    public String getContent() {
        return this.content;
    }

}
