package user.sort;
import entity.Review;
import java.util.*;

public class HelpfulSort implements ISort{

    /**
     * @param reviews an ArrayList of Reviews from the page
     * @return a response model
     */
    public SortResponseModel sort(ArrayList<Review> reviews){
        ArrayList<Integer> likesOrder = new ArrayList<Integer>();
        ArrayList<Review> newOrder = new ArrayList<Review>();
        for (Review review : reviews){
            likesOrder.add(review.getNumLikes());
        }
        likesOrder.sort(Collections.reverseOrder());
        for (int likeCount : likesOrder) {
            for (Review review : reviews) {
                if (review.getNumLikes() == likeCount){
                    newOrder.add(review);
                }
            }
        }
        return new SortResponseModel(newOrder);
    }
}
