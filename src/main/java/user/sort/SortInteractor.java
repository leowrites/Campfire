package user.sort;

/** The sort use case interactor that calls the createSortResponseModel method from the
 * ISortInput input boundary that separates it from the other layers of Clean Architecture.
 */
public class SortInteractor implements ISortInput{

    /** Sorts the ArrayList of reviews in the requestModel by the sorting algorithm stored in
     * the requestModel, and returns the SortResponseModel resulting from that sort.
     * @param requestModel a request model that contains an ArrayList of Reviews to be sorted
     * @return a response model
     */
    @Override
    public SortResponseModel createSortResponseModel(SortRequestModel requestModel){
        return requestModel.getSortingAlgorithm().sort(requestModel.getReviews());
    }
}
