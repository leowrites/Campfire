package user.sort;

public class SortInteractor implements ISortInput{

    @Override
    public SortResponseModel createSortResponseModel(SortRequestModel requestModel){
        return requestModel.getSortingAlgorithm().sort(requestModel.getReviews());
    }
}
