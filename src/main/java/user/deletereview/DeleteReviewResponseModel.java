package user.deletereview;

import entity.Review;

import java.util.ArrayList;

public class DeleteReviewResponseModel {

    private final String message;

    private final ArrayList<Review> newReviews;

    public DeleteReviewResponseModel(String message, ArrayList<Review> reviews){
        this.message = message;
        this.newReviews = reviews;
    }

    public String getMessage(){
        return this.message;
    }

    public ArrayList<Review> getReviews(){
        return this.newReviews;
    }

}
