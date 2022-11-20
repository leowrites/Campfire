package user.sort;
import user.sort.exceptions.SortCriteriaNotFoundException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SortController {

    private final ISortInput interactor;

    @Autowired
    public SortController(ISortInput interactor){
        this.interactor = interactor;
    }

    @GetMapping("/review/sort")
    public SortResponseModel reviewSort(@RequestBody SortRequestModel requestModel){
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