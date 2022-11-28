package user.createCorporate;
import entity.Corporate;
import entity.CorporateRep;
import service.ICorporateGenerateDataAccess;
import user.createCorporate.exceptions.CorporateCreationFailedException;

// Use Case Layer

public class CorporateGenerateInteractor implements ICorporateGenerateInput {

    private final ICorporateGenerateDataAccess dataAccess;
    private final CorporateFactory corporateFactory;

    /**
     * Constructor of the use case interactor
     * @param dataAccess: for the createCorporatePage use case, CorporateGenerateDataAccess will be passed in
     * @param corporateFactory: for the createCorporatePage use case, CorporatePageFactory will be passed in
     */
    public CorporateGenerateInteractor(ICorporateGenerateDataAccess dataAccess, CorporateFactory corporateFactory){
        this.dataAccess = dataAccess;
        this.corporateFactory = corporateFactory;
    }

    /**
     * implement the input boundary here
     * @param requestModel with the user inputs is passed in
     * @return a responseModel is returned
     */

    public CorporateGenerateResponseModel create(CorporateGenerateRequestModel requestModel){

        // if the page exists already, return responseModel with an error message
        try {
            this.dataAccess.checkCorporateExists(requestModel.getCompanyName());
        } catch (CorporateCreationFailedException e){
            return new CorporateGenerateResponseModel(e.getMessage());
        }

        Corporate corporate = corporateFactory.create(
                (CorporateRep) requestModel.getUser(),
                requestModel.getCompanyName(), requestModel.getCompanyInfo());

        dataAccess.createCorporate(corporate);
        return new CorporateGenerateResponseModel(
                "New corporate page successfully created.",
                corporate.getCompanyName(), corporate.getCompanyInfo());
    }

}

