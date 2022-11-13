package useCases.createCorporatePage;
import entity.CorporatePage;
import entity.Page;

public class PageGenerateInteractor implements PageGenerateInputBoundary {

    final PageGenerateDsGateway pageGenerateDsGateway;
    final PageGenerateOutputBoundary pageGenerateOutputBoundary;
    final PageFactory pageFactory;

    public PageGenerateInteractor(PageGenerateDsGateway pageGenerateDsGateway,
                                  PageGenerateOutputBoundary pageGenerateOutputBoundary, PageFactory pageFactory){
        this.pageGenerateDsGateway = pageGenerateDsGateway;
        this.pageGenerateOutputBoundary = pageGenerateOutputBoundary;
        this.pageFactory = pageFactory;
    }

    public PageGenerateResponseModel create(PageGenerateRequestModel requestModel){

        if (pageGenerateDsGateway.existsByPageLabel(requestModel.getInputLabel())){
            return pageGenerateOutputBoundary.prepareFailView("Page already exists.");
        }

        Page page = pageFactory.create(requestModel.getInputLabel(), requestModel.getUser());

        PageGenerateResponseModel pageResponseModel;

        if (requestModel.getPageType().equals("Corporate")){
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

        return pageGenerateOutputBoundary.prepareSuccessView(pageResponseModel);


    }

    public void saveToDs(Page page){
        PageGenerateDsRequestModel pageDsModel = new PageGenerateDsRequestModel(page.getPageLabel(),
                page.getPageOwner());
        pageGenerateDsGateway.save(pageDsModel);
    }



}
