package user.createCorporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorporateGenerateController {
    final ICorporateGenerateInput interactor;

    /**
     *
     * @param interactor is passed to the controller via input boundary
     */

    @Autowired
    public CorporateGenerateController(ICorporateGenerateInput interactor) {
        this.interactor = interactor;
    }

    /**
     *
     * @param requestModel is passed in to the responseModel creation process
     * @return interactor is returned
     */

    @PostMapping("/corporates")
    public CorporateGenerateResponseModel create(CorporateGenerateRequestModel requestModel){
        return interactor.create(requestModel);
    }
}
