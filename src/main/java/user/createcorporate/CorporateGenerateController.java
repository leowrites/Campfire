package user.createcorporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ServerStatus;

import java.security.Principal;

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

    @PostMapping("/corporates")
    public ResponseEntity<CorporateGenerateResponseModel> create(Principal principal,
                                                                 @RequestBody CorporateGenerateRequestModel requestModel){

        //if principal's username is null, or principal's username doesn't match with request model's username
        if (principal.getName().equals(null) || !principal.getName().equals(requestModel.getUserId())) {
            CorporateGenerateResponseModel responseModel = new CorporateGenerateResponseModel(ServerStatus.ERROR, "Bad request.");
            return new ResponseEntity<>(responseModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        CorporateGenerateResponseModel responseModel = this.input.create(requestModel);

        if (responseModel.getStatus() == ServerStatus.SUCCESS){
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(responseModel, HttpStatus.UNPROCESSABLE_ENTITY);}
    }

}
