package user.createCorporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/users/createCorporate")
    public ResponseEntity<CorporateGenerateResponseModel> create(CorporateGenerateRequestModel requestModel){
        CorporateGenerateResponseModel responseModel = this.interactor.create(requestModel);
        if (responseModel.getResultMessage().equals("New corporate page successfully created.")){
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        else {return new ResponseEntity<>(responseModel, HttpStatus.UNPROCESSABLE_ENTITY);}
    }

}
