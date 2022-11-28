package user.deletereview;

import entity.Review;

import java.util.ArrayList;

public class DeleteReviewDataAccess implements IDeleteReviewDataAccess{
    @Override
    public Review getReview(String Id){
        // Connect this to Spring DB, should take the Id and return the Review in the database
        // with the corresponding Id
        Review review = new Review();
        return review;
    }

    @Override
    public ArrayList<Review> getReviews(){
        // Return the database of Reviews
        return new ArrayList<Review>();
    }

    @Override
    public void updateReviews(ArrayList<Review> reviews){
        // Update the reviews DB without this review
    }

}
