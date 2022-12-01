package user.sort;
import java.util.*;
import entity.Review;

/** An interface that is intended for the different sorting algorithms to separate dependency
 * for Clean Architecture. It holds one method, sort, that must be implemented in all classes
 * that implement this interface.
 */
public interface ISort {

    /** Sorts the Reviews in reviews.
     * @param reviews an ArrayList of Reviews.
     * @return a SortResponseModel that holds the sorted Reviews.
     */
    SortResponseModel sort(ArrayList<Review> reviews);
}
