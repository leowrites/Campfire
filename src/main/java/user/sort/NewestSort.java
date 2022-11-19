package user.sort;
import entity.Review;
import java.util.*;

public class NewestSort implements ISort{

    /**
     * @param reviews an ArrayList of Reviews from the page
     * @return a response model
     */
    public SortResponseModel sort(ArrayList<Review> reviews){
        ArrayList<Date> dateOrder = new ArrayList<Date>();
        ArrayList<Review> newOrder = new ArrayList<Review>();
        for (Review review : reviews){
            dateOrder.add(review.getDatePosted());
        }
        dateOrder.sort(Collections.reverseOrder());
        for (Date date : dateOrder) {
            for (Review review : reviews) {
                if (review.getDatePosted().compareTo(date) == 0){
                    newOrder.add(review);
                }
            }
        }
        return new SortResponseModel(newOrder);
    }
}
