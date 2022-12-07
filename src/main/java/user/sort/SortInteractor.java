package user.sort;

import java.util.ArrayList;
import entity.Review;
import service.dao.IReviewDAO;
import service.ServerStatus;
import user.comment.exceptions.ReviewNotFoundException;
import user.sort.exceptions.SortCriteriaNotFoundException;

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
        String sortingCriteria = requestModel.getSortCriteria();
        int internshipId = requestModel.getParentInternshipId();
        SortAlgorithmFactory factory = new SortAlgorithmFactory();

        ArrayList<Review> reviews;
        try{
            reviews = reviewDAO.getReviewsByInternship(internshipId);
        } catch (ReviewNotFoundException e){
            return new SortResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        ISort algorithm;
        try {
            algorithm = factory.createSortAlgorithm(sortingCriteria);
        }
        catch (SortCriteriaNotFoundException e) {
            return new SortResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        ArrayList<Review> sortedReviews = algorithm.sort(reviews);
        ArrayList<Integer> sortedReviewIds = new ArrayList<>();
        for (Review review : sortedReviews){
            sortedReviewIds.add(review.getId());
        }
        return new SortResponseModel(sortedReviewIds, ServerStatus.SUCCESS, "Reviews sorted successfully");
    }
}
