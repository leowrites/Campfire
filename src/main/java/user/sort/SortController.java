package user.sort;
import service.ServerStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** The sort use case controller that connects to Spring. It takes in an object that implements
 * ISortInput that has interactor methods, sorts by sending the correct algorithm to the that object,
 * and puts the response model holding the sorted ids in a ResponseEntity with the http status to
 * send back to the front-end.
 */
@RestController
public class SortController {

    private final ISortInput interactor;

    /** Looks for the BeanConfiguration class to get the interactor.
     * @param interactor a sortInteractor.
     */
    @Autowired
    public SortController(ISortInput interactor){
        this.interactor = interactor;
    }

    /** Creates a SortResponseModel using the inputs in requestModel.
     * @param requestModel the requestModel taken in from the front-end.
     * @return a ResponseEntity holding a SortResponseModel and an HttpStatus
     */
    @GetMapping("/reviews/sort")
    public ResponseEntity<SortResponseModel> reviewSort(@RequestBody SortRequestModel requestModel){
        SortResponseModel responseModel = interactor.createSortResponseModel(requestModel);
        if (responseModel.getStatus() == ServerStatus.SUCCESS){
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(responseModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}