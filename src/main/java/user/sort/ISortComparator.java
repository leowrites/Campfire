package user.sort;
import entity.Review;

public interface ISortComparator {

    int compareToHelpful(Review otherReview);

    int compareToHighestRating(Review otherReview);

    int compareToNewest(Review otherReview);
}
