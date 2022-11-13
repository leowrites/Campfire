package delete_comment;

import entity.Review;

import java.util.ArrayList;

public class DeleteCommentRequestModel {

    private final ArrayList<Review> comments;

    private final int id;

    private final int access_level;

    public DeleteCommentRequestModel(ArrayList<Review> comments,
                                     int id,
                                     int access_level){
        this.comments = comments;
        this.id = id;
        this.access_level = access_level;
    }

    public ArrayList<Review> getcomments(){
        return this.comments;
    }

    public int getid(){
        return this.id;
    }

    public int getaccesslevel(){
        return this.access_level;
    }

}
