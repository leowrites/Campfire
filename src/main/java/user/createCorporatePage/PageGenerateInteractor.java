package user.createCorporatePage;
import entity.CorporatePage;
import entity.Page;
import service.IPageGenerateDataAccess;
import service.PageGenerateDataAccess;
import service.ServerStatus;
import user.createCorporatePage.exceptions.PageCreationFailed;

public class PageGenerateInteractor implements IPageGenerateInput {

    private final IPageGenerateDataAccess dataAccess;
//    private final PageGenerateOutputBoundary pageGenerateOutputBoundary;
    private final PageFactory pageFactory;

    public PageGenerateInteractor(IPageGenerateDataAccess dataAccess, PageFactory pageFactory){
        this.dataAccess = dataAccess;
//        this.pageGenerateOutputBoundary = pageGenerateOutputBoundary;
        this.pageFactory = pageFactory;
    }

    public PageGenerateResponseModel create(PageGenerateRequestModel requestModel){

//        if (pageGenerateDsGateway.existsByPageLabel(requestModel.getInputLabel())){
//            return pageGenerateOutputBoundary.prepareFailView("Page already exists.");
//
//            //return a responseModel instead?
//        }

        try {
            Page page = pageFactory.create(requestModel.getInputLabel(), requestModel.getUser());
            PageGenerateResponseModel pageResponseModel;

            if (requestModel.getPageType() == 1){
                CorporatePage corporatePage = (CorporatePage) page;
                CorporatePageFactory corporatePageFactory = new CorporatePageFactory();
                corporatePage = corporatePageFactory.addInfo(corporatePage,
                        requestModel.getCompanyName(), requestModel.getCompanyInfo());

                saveToDs(corporatePage);

                pageResponseModel = new PageGenerateResponseModel(
                        "New corporate page successfully created.",
                        corporatePage.getCompanyName(), corporatePage.getCompanyInfo());

            } else {
                saveToDs(page);
                pageResponseModel = new PageGenerateResponseModel(
                        "page successfully created.");
            }

            return pageResponseModel;

        } catch (PageCreationFailed e) {
            return new PageGenerateResponseModel("Page generation failed!");
        }


//
//        return pageGenerateOutputBoundary.prepareSuccessView(pageResponseModel);
//        // return a responseModel instead?


    }

    public void saveToDs(Page page){
        PageGenerateDsRequestModel pageDsModel = new PageGenerateDsRequestModel(page.getPageLabel(),
                page.getPageOwner());
//        pageGenerateDsGateway.save(pageDsModel);
        dataAccess.createPage(page);
    }

    public void saveToDs(CorporatePage corporatePage){
        PageGenerateDsRequestModel pageDsModel = new PageGenerateDsRequestModel(corporatePage.getPageLabel(),
                corporatePage.getPageOwner());
        dataAccess.createPage(corporatePage);
    }



}
