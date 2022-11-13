package delete_comment;

import entity.Review;

import java.util.ArrayList;
import java.util.Objects;

public class DeleteCommentHandler {

    private final String id;

    private final ArrayList<Review> comments;

    public DeleteCommentHandler(String id, ArrayList<Review> comments){
        this.id = id;
        this.comments = comments;
    }

    public void deletecomment(String id, ArrayList<Review> comments){
        int size = comments.size();
        for (int i = 0; i < size; i++){
            if (id.equals(comments.get(i).getid())){
                comments.remove(i);
                break;
            }
        }
    }
}
