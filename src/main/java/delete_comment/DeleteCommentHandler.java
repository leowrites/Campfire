package delete_comment;

import entity.Comment;
import entity.Review;

import java.util.ArrayList;

public class DeleteCommentHandler {

    private final String id;

    private final ArrayList<Comment> comments;

    public DeleteCommentHandler(String id, ArrayList<Comment> comments){
        this.id = id;
        this.comments = comments;
    }

    public void deletecomment(String id, ArrayList<Comment> comments){
        int size = comments.size();
        for (int i = 0; i < size; i++){
            if (id.equals(comments.get(i).getid())){
                comments.remove(i);
                break;
            }
        }
    }
}
