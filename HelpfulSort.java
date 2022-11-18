package user.sort;
import entity.Review;
import java.util.*;

public class HelpfulSort implements ISort{

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
