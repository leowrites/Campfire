package user.sort;
import entity.Review;
import java.util.*;

/** A sorting algorithm to be created by a SortAlgorithmFactory that sorts
 * the given ArrayList of reviews by the helpful algorithm.
 */
public class HelpfulSort implements ISort{

    /** Sorts reviews by the most likes in descending order.
     * @param reviews an ArrayList of Reviews from the page
     * @return a response model
     */
    public SortResponseModel sort(ArrayList<Review> reviews){
        reviews.sort(Review::compareToHelpful);
        return new SortResponseModel(reviews);
    }
}
