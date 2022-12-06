package user.sort;
import service.ServerStatus;
import user.sort.exceptions.SortCriteriaNotFoundException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** The sort use case controller that connects to Spring. It takes in a sortCriteria
 * from the user input in front-end, sorts by sending the criteria to the interactor,
 * and gives the sorted responseModel back to the front-end.
 */
@RestController
public class SortController {

    private final ISortInput interactor;

    /** This looks for the SortConfig class to get the interactor.
     * @param interactor a sortInteractor.
     */
    @Autowired
    public SortController(ISortInput interactor){
        this.interactor = interactor;
    }

    /** Sorts the given ArrayList of Reviews in requestModel and returns a sortResponseModel.
     * @param requestModel the requestModel taken in from the front-end.
     * @return a responseModel holding the sorted ArrayList of Reviews.
     */
    @GetMapping("/reviews/sort")
    public ResponseEntity<SortResponseModel> reviewSort(@RequestBody SortRequestModel requestModel){
        SortAlgorithmFactory factory = new SortAlgorithmFactory(requestModel.getSortCriteria());
        try{
            requestModel.setSortingAlgorithm(factory.createSortAlgorithm());
        }
        catch(SortCriteriaNotFoundException e){
            System.out.println(e.getMessage());
        }
        SortResponseModel responseModel = interactor.createSortResponseModel(requestModel);

        if (responseModel.getStatus() == ServerStatus.SUCCESS){
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(responseModel, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}