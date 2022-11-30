package user.deletecomment;

import entity.Comment;

import java.util.ArrayList;

public class DeleteCommentHandler {

    private final String commentId;

    private final ArrayList<Comment> comments;

    public DeleteCommentHandler(String commentId, ArrayList<Comment> comments){
        this.commentId = commentId;
        this.comments = comments;
    }

    public ArrayList<Comment> deleteComment(){
        /*
        Takes in a String (commentId) and the Arraylist (comments) that contains the comment
        Deletes the comment from comments
        Return new Arraylist of comments
        */
        int size = this.comments.size();
        for (int i = 0; i < size; i++){
            if (this.commentId.equals(this.comments.get(i).getId())){
                this.comments.remove(i);
                break;
            }
        }
        return this.comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
