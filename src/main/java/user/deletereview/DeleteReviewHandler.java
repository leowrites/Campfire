package user.deletereview;

import entity.Internship;

import java.util.ArrayList;

public class DeleteReviewHandler {

    private final Internship internship;

    private final int reviewId;

    public DeleteReviewHandler(Internship internship, int reviewId){
        this.internship = internship;
        this.reviewId = reviewId;
    }

    public Internship deleteReview(){
        ArrayList<Integer>  reviewList = internship.getReviews();
        int size = reviewList.size();
        for (int i = 0; i < size; i++){
            if (reviewId == reviewList.get(i)){
                reviewList.remove(i);
                break;
            }
        }
        internship.setReviews(reviewList);
        return internship;
    }
}
