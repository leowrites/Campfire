package user.comment;

import entity.Comment;

public class CommentFactory {

    public Comment createComment(String userId, String content) {
        return new Comment(userId, content);
    }

}
