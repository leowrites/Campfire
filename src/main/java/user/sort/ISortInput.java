package user.sort;

/** An interface that is intended to be implemented by the SortInteractor class. Holds one
 * method, createSortResponseModel, that must be implemented in all classes that implement
 * this interface.
 */
public interface ISortInput {

    /** Creates a SortResponseModel using the input from the requestModel.
     * @param requestModel a requestModel.
     * @return a SortResponseModel
     */
    SortResponseModel createSortResponseModel(SortRequestModel requestModel);
}
