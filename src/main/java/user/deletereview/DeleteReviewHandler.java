package user.deletereview;

import entity.Internship;

import java.util.List;

/** A class in the deletereview use case that handles the deletion of the Review whose id is the
 * int reviewId from the Internship internship, both of which are taken in on initialization.
 */
public class DeleteReviewHandler {

    private final Internship internship;

    private final int reviewId;

    public DeleteReviewHandler(Internship internship, int reviewId){
        this.internship = internship;
        this.reviewId = reviewId;
    }

    /** Deletes the review with id reviewId from internship.
     */
    public Internship deleteReview(){
        List<Integer> reviewList = internship.getReviews();
        int size = reviewList.size();
        for (int i = 0; i < size; i++){
            if (reviewId == reviewList.get(i)){
                reviewList.remove(i);
                break;
            }
        }
        internship.setReviews(reviewList);
        return internship;
    }
}
