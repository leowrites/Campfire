package user.sort;

/** The sort use case interactor that calls the createSortResponseModel method from the
 * ISortInput input boundary.
 */
public class SortInteractor implements ISortInput{

    /** Sorts the reviews in requestModel using the appropriate sorting algorithm.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public SortResponseModel createSortResponseModel(SortRequestModel requestModel){
        return requestModel.getSortingAlgorithm().sort(requestModel.getReviews());
    }
}
