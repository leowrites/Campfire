package user.deletecomment;

import entity.Comment;

import java.util.ArrayList;

public class DeleteCommentResponseModel {

    private final String message;

    private final ArrayList<Comment> comments;

    public DeleteCommentResponseModel(String message, ArrayList<Comment> comments){

        this.message = message;
        this.comments = comments;

    }

    public String getMessage(){
        return this.message;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
