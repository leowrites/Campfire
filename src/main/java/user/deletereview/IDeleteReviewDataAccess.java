package user.deletereview;

import entity.Review;

import java.util.ArrayList;

public interface IDeleteReviewDataAccess {

    public Review getReview(String Id);

    public ArrayList<Review> getReviews();

    public void updateReviews(ArrayList<Review> reviews);

}
