package user.sort;
import user.sort.exceptions.SortCriteriaNotFoundException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SortController {

    @PostMapping("/request")
    public SortResponseModel postController(@RequestBody SortRequestModel requestModel){
        SortInteractor interactor = new SortInteractor();
        SortAlgorithmFactory factory = new SortAlgorithmFactory(requestModel.getSortCriteria());
        try{
            requestModel.setSortingAlgorithm(factory.createSortAlgorithm());
        }
        catch(SortCriteriaNotFoundException e){
            System.out.println(e.getMessage());
        }
        return interactor.createSortResponseModel(requestModel);
    }
}
