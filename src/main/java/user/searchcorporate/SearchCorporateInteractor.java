package user.searchcorporate;

import entity.Corporate;
import service.ServerStatus;
import service.dao.ICorporateDAO;

import java.util.ArrayList;


public class SearchCorporateInteractor implements ISearchCorporateInput {
    private final ICorporateDAO dataAccess;


    public SearchCorporateInteractor(ICorporateDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    public SearchCorporateResponseModel search(SearchCorporateRequestModel requestModel){
        ArrayList<Corporate> corporateList = this.dataAccess.getCorporatesWithSubstring(requestModel.getSearchterm());
        ArrayList<String> corporateNames = new ArrayList<String>();
        for (Corporate corporate: corporateList){
            corporateNames.add(corporate.getCompanyName());
        }
        if (!corporateNames.isEmpty()) { // if there are corporate names to return
            return new SearchCorporateResponseModel(corporateNames, ServerStatus.SUCCESS, "success");
        } else { //list is empty, i,e no search terms
            return new SearchCorporateResponseModel(null, ServerStatus.SUCCESS, "No companies found");
        }
    }
}
