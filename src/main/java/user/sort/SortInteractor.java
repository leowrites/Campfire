package user.sort;

import java.util.ArrayList;
import entity.Review;
import service.dao.IReviewDAO;

/** The sort use case interactor that calls the createSortResponseModel method from the
 * ISortInput input boundary that separates it from the other layers of Clean Architecture.
 */
public class SortInteractor implements ISortInput{

    private final IReviewDAO reviewDAO;

    public SortInteractor(IReviewDAO reviewDAO){
        this.reviewDAO = reviewDAO;
    }

    /** Sorts the ArrayList of reviews in the requestModel by the sorting algorithm stored in
     * the requestModel, and returns the SortResponseModel resulting from that sort.
     * @param requestModel a request model that contains an ArrayList of Reviews to be sorted
     * @return a response model
     */
    @Override
    public SortResponseModel createSortResponseModel(SortRequestModel requestModel){
        ArrayList<Review> reviews = new ArrayList<>();
        for (int id : requestModel.getReviews()){
            reviews.add(reviewDAO.getReview(id));
        }
        ArrayList<Review> sortedReviews = requestModel.getSortingAlgorithm().sort(reviews);
        ArrayList<Integer> sortedReviewIds = new ArrayList<>();
        for (Review review : sortedReviews){
            sortedReviewIds.add(review.getId());
        }
        return new SortResponseModel(sortedReviewIds);
    }
}
