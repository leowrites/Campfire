package user.createCorporatePage;


import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageGenerateController {
    final IPageGenerateInput interactor;

    @Autowired
    public PageGenerateController(IPageGenerateInput interactor) {
        this.interactor = interactor;
    }

    @PostMapping
    public PageGenerateResponseModel create(PageGenerateRequestModel requestModel){
        return interactor.create(requestModel);
    }


/**
 * The code that is commented out below was for the clean architecture framework
 * where "input boundary" and "gateway" are used.
 * As we are implementing Spring instead, the code below is no long be needed and only kept as comments
 * for my own reference (ie. easier for me to understand the logic and debug).
 */

//    final PageGenerateInputBoundary pageInput;

//    public PageGenerateController(PageGenerateInputBoundary pageGateway){
//        this.pageInput = pageGateway;
//    }

    // The requestModel uses the number of arguments to distinguish the different types of pages
//    PageGenerateResponseModel create(int pageType, String inputLabel, User user){
//        PageGenerateRequestModel requestModel = new PageGenerateRequestModel(pageType, inputLabel, user);
//        return pageInput.create(requestModel);
//
//    }
//
//    // This is requestModel for a corporatePage
//    // overloading
//    PageGenerateResponseModel create(int pageType,
//                                     String inputLabel, User user, String companyName, String companyInfo){
//        PageGenerateRequestModel requestModel = new PageGenerateRequestModel(pageType, inputLabel, user,
//                companyName, companyInfo);
//        return pageInput.create(requestModel);
//
//    }

}
