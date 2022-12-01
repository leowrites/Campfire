package user.deletecomment;

import entity.Comment;

import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

public class DeleteCommentHandler {

    private final String commentId;

    private final ArrayList<Comment> comments;

    public DeleteCommentHandler(String commentId, ArrayList<Comment> comments){
        this.commentId = commentId;
        this.comments = comments;
    }

    public boolean deleteComment(){
        /*
        int size = this.comments.size();
        for (int i = 0; i < size; i++){
            if (this.commentId.equals(this.comments.get(i).getId())){
                this.comments.remove(i);
                break;
            }
        }
        return this.comments;

         */
        return TRUE;

    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
