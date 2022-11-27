package user.createCorporatePage;
import entity.CorporatePage;
import entity.CorporateRep;
import service.IPageGenerateDataAccess;
import user.createCorporatePage.exceptions.PageCreationFailedException;

// Use Case Layer

public class PageGenerateInteractor implements IPageGenerateInput {

    private final IPageGenerateDataAccess dataAccess;
    private final PageFactory pageFactory;

    /**
     * Constructor of the use case interactor
     * @param dataAccess: for the createCorporatePage use case, PageGenerateDataAccess will be passed in
     * @param pageFactory: for the createCorporatePage use case, CorporatePageFactory will be passed in
     */
    public PageGenerateInteractor(IPageGenerateDataAccess dataAccess, PageFactory pageFactory){
        this.dataAccess = dataAccess;
        this.pageFactory = pageFactory;
    }

    /**
     * implement the input boundary here
     * @param requestModel with the user inputs is passed in
     * @return a responseModel is returned
     */

    public PageGenerateResponseModel create(PageGenerateRequestModel requestModel){

        // if the page exists already, return responseModel with an error message
        try {
            this.dataAccess.checkPageExists(requestModel.getInputLabel());
        } catch (PageCreationFailedException e){
            return new PageGenerateResponseModel(e.getMessage());
        }

        // if the PageType is CORPORATE, return a responseModel with the corporate name and info

        if (requestModel.getPageType() == PageType.CORPORATE){
            CorporatePage corporatePage = pageFactory.create(requestModel.getInputLabel(),
                    (CorporateRep) requestModel.getUser(),
                    requestModel.getCompanyName(), requestModel.getCompanyInfo());

            dataAccess.createPage(corporatePage);
            return new PageGenerateResponseModel(
                    "New corporate page successfully created.",
                    corporatePage.getCompanyName(), corporatePage.getCompanyInfo());
        }

        // if more PageTypes are implemented in the future, elseif can be added here

        else{
            return new PageGenerateResponseModel("Corporate page creation failed.");
        }
    }

}
