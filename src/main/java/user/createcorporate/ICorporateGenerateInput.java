package user.createcorporate;

/** An interface that is intended to be implemented by the CorporateGenerateInteractor class.
 * Holds one method, create, that must be implemented in all classes that implement
 * this interface.
 */
public interface ICorporateGenerateInput {
    /** Creates a CorporateGenerateResponseModel using the input from requestModel
     * @param requestModel a CorporateGenerateRequestModel
     * @return a CorporateGenerateResponseModel
     */
    CorporateGenerateResponseModel create(CorporateGenerateRequestModel requestModel);
}
