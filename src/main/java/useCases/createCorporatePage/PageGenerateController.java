package useCases.createCorporatePage;

import entity.User;

public class PageGenerateController {
    final PageGenerateInputBoundary pageInput;

    public PageGenerateController(PageGenerateInputBoundary pageGateway){
        this.pageInput = pageGateway;
    }

    // The requestModel uses the number of arguments to distinguish the different types of pages
    PageGenerateResponseModel create(int pageType, String inputLabel, User user){
        PageGenerateRequestModel requestModel = new PageGenerateRequestModel(pageType, inputLabel, user);
        return pageInput.create(requestModel);

    }

    // This is requestModel for a corporatePage
    // overloading
    PageGenerateResponseModel create(int pageType,
                                     String inputLabel, User user, String companyName, String companyInfo){
        PageGenerateRequestModel requestModel = new PageGenerateRequestModel(pageType, inputLabel, user,
                companyName, companyInfo);
        return pageInput.create(requestModel);

    }

}
