package user.deletereview;

import entity.Review;

import java.util.ArrayList;

public class DeleteReviewHandler {

    private final String reviewId;

    private final ArrayList<Review> reviews;

    public DeleteReviewHandler(String reviewId, ArrayList<Review> reviews){
        this.reviewId = reviewId;
        this.reviews = reviews;
    }

    public ArrayList<Review> deleteReview(){
        // Takes in a review id, and deletes the review from the Review DB
        int size = this.reviews.size();
        for (int i = 0; i < size; i++){
            if (this.reviewId.equals(this.reviews.get(i).getId())){
                this.reviews.remove(i);
                break;
            }
        }
        return this.reviews;
    }

    public String getReviewId() {
        return reviewId;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
