package user.sort;
import entity.Review;

/** An interface that is intended for the Review class to implement comparators for the information
 * in Review that will be used when sorting. It holds three methods, compareToHelpful,
 * compareToHighestRating, and compareToNewest, that must be implemented in all classes that
 * implement this interface.
 */
public interface ISortComparator {

    /** Compares the object to another object and returns an int value corresponding to the
     * order of the compared objects for helpfulness.
     * @param otherReview a second Review to compare to.
     * @return either 0, -1 or 1 corresponding to the order of the compared objects.
     */
    int compareToHelpful(Review otherReview);

    /** Compares the object to another object and returns an int value corresponding to the
     * order of the compared objects for highest rating.
     * @param otherReview a second Review to compare to.
     * @return either 0, -1 or 1 corresponding to the order of the compared objects.
     */
    int compareToHighestRating(Review otherReview);

    /** Compares the object to another object and returns an int value corresponding to the
     * order of the compared objects for newest.
     * @param otherReview a second Review to compare to.
     * @return either 0, -1 or 1 corresponding to the order of the compared objects.
     */
    int compareToNewest(Review otherReview);
}
