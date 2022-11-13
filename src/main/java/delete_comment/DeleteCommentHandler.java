package delete_comment;

import entity.Review;

import java.util.ArrayList;

public class DeleteCommentHandler {

    private final int id;

    private final ArrayList<Review> comments;

    public DeleteCommentHandler(int id, ArrayList<Review> comments){
        this.id = id;
        this.comments = comments;
    }

    public void deletecomment(int id, ArrayList<Review> comments){
        int size = comments.size();
        for (int i = 0; i < size; i++){
            if (comments.get(i).getid() == id){
                comments.remove(i);
                break;
            }
        }
    }
}
