package usecases.sort;

/** An interface that is intended for the SortInteractor class to separate the dependency
 * for Clean Architecture. It holds one method, createSortRepsonseModel, that must be implemented
 * in all classes that implement this interface.
 */
public interface ISortInput {

    /** Creates a SortResponseModel using the input from the requestModel.
     * @param requestModel a requestModel.
     * @return a SortResponseModel
     */
    SortResponseModel createSortResponseModel(SortRequestModel requestModel);
}
