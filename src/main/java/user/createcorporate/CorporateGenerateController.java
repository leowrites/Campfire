package user.createcorporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ServerStatus;

import java.security.Principal;

/** The createcorporate use case controller that connects to Spring. Takes in a
 * CorporateGenerateRequestModel from the user input in front-end, creates a
 * CorporateGenerateResponseModel by sending the request model to the interactor, and puts the
 * response model in a ResponseEntity with the http status to send back to the front-end.
 */
@RestController
public class CorporateGenerateController {
    final ICorporateGenerateInput input;

    @Autowired
    public CorporateGenerateController(ICorporateGenerateInput input) {
        this.input = input;
    }

    /** Creates a CorporateGenerateResponseModel using the inputs in requestModel.
     * @param requestModel the CorporateGenerateRequestModel taken in from the front-end
     * @return a ResponseEntity holding a CorporateGenerateResponseModel and an HttpStatus
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
