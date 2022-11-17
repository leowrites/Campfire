package user.delete_comment;

import entity.Comment;

import java.util.ArrayList;

public class DeleteCommentHandler {

    private final String commentId;

    private final ArrayList<Comment> comments;

    public DeleteCommentHandler(String commentId, ArrayList<Comment> comments){
        this.commentId = commentId;
        this.comments = comments;
    }

    public void deleteComment(String id, ArrayList<Comment> comments){
        int size = comments.size();
        for (int i = 0; i < size; i++){
            if (id.equals(comments.get(i).getid())){
                comments.remove(i);
                break;
            }
        }
    }
}
