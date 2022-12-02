package user.createcorporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorporateGenerateController {
    final ICorporateGenerateInput input;

    /**
     *
     * @param input is passed to the controller via input boundary
     */

    @Autowired
    public CorporateGenerateController(ICorporateGenerateInput input) {
        this.input = input;
    }

    /**
     *
     * @param requestModel is passed in to the responseModel creation process
     * @return interactor is returned
     */

    @PostMapping("/users/createCorporate")
    public ResponseEntity<CorporateGenerateResponseModel> create(CorporateGenerateRequestModel requestModel){
        CorporateGenerateResponseModel responseModel = this.input.create(requestModel);
        String status = responseModel.getStatus().toString();
        if (status.equals("success")){
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(responseModel, HttpStatus.UNPROCESSABLE_ENTITY);}
    }

}
