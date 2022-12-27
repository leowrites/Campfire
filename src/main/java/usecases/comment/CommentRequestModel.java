package usecases.comment;

import java.util.UUID;

/** A request model for the comment use case that frames the input data into an object. Holds
 * a String representation of the user's id in userId, a String of the type of the parent to the
 * comment in parentType, an int representation of the parent's id in parentId, and a String
 * of the content of the comment in content.
 */
public class CommentRequestModel {
    private final String userId;
    private final String parentType;
    private final UUID parentId;
    private final String content;

    public CommentRequestModel(String userId, String parentType, UUID parentId, String content) {
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

    public UUID getParentId() {
        return this.parentId;
    }

    public String getContent() {
        return this.content;
    }

}
