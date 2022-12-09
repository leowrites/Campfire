package usecases.votehelpful;

/**
 * An interface that is intended to be implemented by the HelpfulInteractor class.
 * Holds one method, create, that must be implemented in all classes that implement
 * this interface.
 */
public interface IHelpfulInputBoundary {
    /** Creates a HelpfulResponseModel using the input from requestModel
     * @param requestModel a HelpfulRequestModel
     * @return a HelpfulResponseModel
     */
    HelpfulResponseModel create(HelpfulRequestModel requestModel);
}
