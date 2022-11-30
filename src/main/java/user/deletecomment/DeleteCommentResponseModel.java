package user.deletecomment;

import entity.Comment;

import java.util.ArrayList;

public class DeleteCommentResponseModel {

    private final String message;

    private final ArrayList<Comment> newComments;

    public DeleteCommentResponseModel(String message, ArrayList<Comment> newComments){

        this.message = message;
        this.newComments = newComments;

    }

    public String getMessage(){
        return this.message;
    }

    // This getter is never used as of now, should be used later by data access class
    public ArrayList<Comment> getNewComments(){
        return this.newComments;
    }
}
