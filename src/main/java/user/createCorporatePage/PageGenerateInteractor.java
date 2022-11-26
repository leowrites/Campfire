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

    public PageGenerateResponseModel create(PageGenerateRequestModel requestModel){

        try {
            this.dataAccess.checkPageExists(requestModel.getInputLabel());
        } catch (PageCreationFailedException e){
            return new PageGenerateResponseModel(e.getMessage());
        }

        if (requestModel.getPageType() == 1){
            CorporatePage corporatePage = pageFactory.create(requestModel.getInputLabel(),
                    (CorporateRep) requestModel.getUser(),
                    requestModel.getCompanyName(), requestModel.getCompanyInfo());

            dataAccess.createPage(corporatePage);
            return new PageGenerateResponseModel(
                    "New corporate page successfully created.",
                    corporatePage.getCompanyName(), corporatePage.getCompanyInfo());
        }

        else{
            return new PageGenerateResponseModel("Not able to create a new page.");
        }
    }

}
