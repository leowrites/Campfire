package user.sort;
import entity.Review;
import java.util.*;

public class HelpfulSort implements ISort{

    /**
     * @param reviews an ArrayList of Reviews from the page
     * @return a response model
     */
    public SortResponseModel sort(ArrayList<Review> reviews){
        ArrayList<Review> newOrder = new ArrayList<>(reviews);
        newOrder.sort(Review::compareToHelpful);
        return new SortResponseModel(newOrder);
    }
}
