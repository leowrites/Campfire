package user.createCorporatePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageGenerateController {
    final IPageGenerateInput interactor;

    /**
     *
     * @param interactor is passed to the controller via input boundary
     */

    @Autowired
    public PageGenerateController(IPageGenerateInput interactor) {
        this.interactor = interactor;
    }

    /**
     *
     * @param requestModel is passed in to the responseModel creation process
     * @return interactor is returned
     */

    @PostMapping("/pages")
    public PageGenerateResponseModel create(PageGenerateRequestModel requestModel){
        return interactor.create(requestModel);
    }
}
