package user.sort;
import entity.Review;
import java.util.*;

/** A sorting algorithm to be created by a SortAlgorithmFactory that sorts
 * the given ArrayList of reviews by the highest rating algorithm.
 */
public class HighestRatingSort implements ISort{

    /** Sorts reviews by the highest rating in descending order.
     * @param reviews an ArrayList of Reviews from the page
     * @return a response model
     */
    public ArrayList<Review> sort(ArrayList<Review> reviews){
        reviews.sort(Review::compareToHighestRating);
        return reviews;
    }
}
