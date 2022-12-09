package usecases.sort;

import java.util.ArrayList;
import entity.Review;
import service.dao.IReviewDAO;
import service.ServerStatus;
import usecases.comment.exceptions.ReviewNotFoundException;
import usecases.sort.exceptions.SortCriteriaNotFoundException;

/** The sort use case interactor that calls the createSortResponseModel method from the
 * ISortInput input boundary.
 */
public class SortInteractor implements ISortInput{

    private final IReviewDAO reviewDAO;
    private final SortAlgorithmFactory sortAlgorithmFactory;

    public SortInteractor(IReviewDAO reviewDAO, SortAlgorithmFactory sortAlgorithmFactory){
        this.reviewDAO = reviewDAO;
        this.sortAlgorithmFactory = sortAlgorithmFactory;
    }

    /** Sorts the reviews in requestModel using the appropriate sorting algorithm.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public SortResponseModel createSortResponseModel(SortRequestModel requestModel){
        String sortingCriteria = requestModel.getSortCriteria();
        int internshipId = requestModel.getParentInternshipId();

        ArrayList<Review> reviews;
        try{
            reviews = reviewDAO.getReviewsByInternship(internshipId);
        } catch (ReviewNotFoundException e){
            return new SortResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        ISort algorithm;
        try {
            algorithm = sortAlgorithmFactory.createSortAlgorithm(sortingCriteria);
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
