package user.deletecomment;

import entity.Review;

import java.util.ArrayList;

public class DeleteParentReview {

    private final Review parentReview;

    private final int commentId;

    public DeleteParentReview(Review parentReview, int commentId){
        this.parentReview = parentReview;
        this.commentId = commentId;
    }

    public Review deleteComment(){
        ArrayList<Integer> commentList = parentReview.getComments();
        int size = commentList.size();
        for (int i = 0; i < size; i++) {
            if (commentId == commentList.get(i)) {
                commentList.remove(i);
                break;
            }
        }
        parentReview.setComments(commentList);
        return parentReview;
    }
}
