package user.sort;
import entity.Review;
import java.util.*;

/** A sorting algorithm to be created by a SortAlgorithmFactory that sorts
 * the given ArrayList of reviews by the newest algorithm.
 */
public class NewestSort implements ISort{

    /** Sorts reviews by the most recent date in descending order.
     * @param reviews an ArrayList of Reviews from the page
     * @return a response model
     */
    public SortResponseModel sort(ArrayList<Review> reviews){
        reviews.sort(Review::compareToNewest);
        return new SortResponseModel(reviews);
    }
}
