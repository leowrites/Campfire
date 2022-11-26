package user.createCorporatePage;
import entity.CorporatePage;
import entity.Page;
import service.IPageGenerateDataAccess;
import user.createCorporatePage.exceptions.PageCreationFailedException;
import java.lang.Throwable;

public class PageGenerateInteractor implements IPageGenerateInput {

    private final IPageGenerateDataAccess dataAccess;
    private final PageFactory pageFactory;

    public PageGenerateInteractor(IPageGenerateDataAccess dataAccess, PageFactory pageFactory){
        this.dataAccess = dataAccess;
        this.pageFactory = pageFactory;
    }

    public PageGenerateResponseModel create(PageGenerateRequestModel requestModel){

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

        } catch (PageCreationFailedException e) {
            return new PageGenerateResponseModel("Page generation failed!");
        }



    }

    public void saveToDs(Page page) {
        PageGenerateDsRequestModel pageDsModel = new PageGenerateDsRequestModel(page.getPageLabel(),
                page.getPageOwner());
        dataAccess.createPage(page);
    }

    public void saveToDs(CorporatePage corporatePage){
        PageGenerateDsRequestModel pageDsModel = new PageGenerateDsRequestModel(corporatePage.getPageLabel(),
                corporatePage.getPageOwner());
        dataAccess.createPage(corporatePage);
    }



}
