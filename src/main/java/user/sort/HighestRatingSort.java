package user.sort;
import entity.Review;
import java.util.*;

public class HighestRatingSort implements ISort{

    /**
     * @param reviews an ArrayList of Reviews from the page
     * @return a response model
     */
    public SortResponseModel sort(ArrayList<Review> reviews){
        reviews.sort(Review::compareToHighestRating);
        return new SortResponseModel(reviews);
    }
}
