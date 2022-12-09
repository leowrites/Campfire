package usecases.sort;
import java.util.*;
import entity.Review;

/** An interface that is intended to be implemented by the different sorting algorithms.
 * Holds one method, sort, that must be implemented in all classes that implement this interface.
 */
public interface ISort {

    /** Sorts the Reviews in reviews.
     * @param reviews an ArrayList of Reviews.
     * @return an ArrayList of Reviews that have been sorted.
     */
    ArrayList<Review> sort(ArrayList<Review> reviews);
}
