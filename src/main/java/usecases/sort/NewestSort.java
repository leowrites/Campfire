package usecases.sort;
import entity.Review;
import java.util.*;

/** A sorting algorithm to be created by a SortAlgorithmFactory that sorts
 * the given ArrayList of reviews by the newest algorithm.
 */
public class NewestSort implements ISort{

    /** Sorts reviews by the most recent date in descending order.
     * @param reviews an ArrayList of Reviews from the page
     * @return an ArrayList of sorted Reviews
     */
    public ArrayList<Review> sort(ArrayList<Review> reviews){
        reviews.sort(Review::compareToNewest);
        return reviews;
    }
}
