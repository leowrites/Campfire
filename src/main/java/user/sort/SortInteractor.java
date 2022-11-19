package user.sort;

public class SortInteractor implements ISortInput{

    /**
     * @param requestModel a request model that contains an ArrayList of Reviews to be sorted
     * @return a response model
     */
    @Override
    public SortResponseModel createSortResponseModel(SortRequestModel requestModel){
        return requestModel.getSortingAlgorithm().sort(requestModel.getReviews());
    }
}
