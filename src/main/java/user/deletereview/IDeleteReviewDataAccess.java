package user.deletereview;

import entity.Review;

import java.util.ArrayList;

public interface IDeleteReviewDataAccess {

    Review getReview(String Id);

    ArrayList<Review> getReviews();

    void updateReviews(ArrayList<Review> reviews);

}
