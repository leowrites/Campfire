package user.sort;
import entity.Review;
import java.util.*;

public class HighestRatingSort implements ISort{

    public SortResponseModel sort(ArrayList<Review> reviews){
        ArrayList<Integer> ratingOrder = new ArrayList<Integer>();
        ArrayList<Review> newOrder = new ArrayList<Review>();
        for (Review review : reviews){
            ratingOrder.add(review.getRating());
        }
        ratingOrder.sort(Collections.reverseOrder());
        for (int ratingCount : ratingOrder) {
            for (Review review : reviews) {
                if (review.getRating() == ratingCount){
                    newOrder.add(review);
                }
            }
        }
        return new SortResponseModel(newOrder);
    }
}
