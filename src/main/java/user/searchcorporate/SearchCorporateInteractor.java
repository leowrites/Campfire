package user.searchcorporate;

import entity.Corporate;
import service.ServerStatus;
import service.dao.ICorporateDAO;

import java.util.ArrayList;

/**
 * This class is an implementation of the ISearchCorporateInput interface.
 * It uses the ICorporateDAO data access object to search for corporate accounts in the data store.
 */
public class SearchCorporateInteractor implements ISearchCorporateInput {
    private final ICorporateDAO dataAccess;


    public SearchCorporateInteractor(ICorporateDAO dataAccess) {
        this.dataAccess = dataAccess;
    }


    /**
     * This method searches for corporate accounts in the data store that match the given search term.
     * It returns a response model containing a list of the company names of the matching corporate accounts.
     * If no matching corporate accounts are found, the list is empty.
     *
     * @param requestModel The search request data, including the search term
     * @return A list of company names of matching corporate accounts
     */
    public SearchCorporateResponseModel search(SearchCorporateRequestModel requestModel){
        ArrayList<Corporate> corporateList = this.dataAccess.getCorporatesWithSubstring(requestModel.getSearchTerm());
        ArrayList<String> corporateNames = new ArrayList<String>();
        for (Corporate corporate: corporateList){
            corporateNames.add(corporate.getCompanyName());
        }
        if (!corporateNames.isEmpty()) { // if there are corporate names to return
            return new SearchCorporateResponseModel(corporateNames, ServerStatus.SUCCESS, "success");
        } else { //list is empty, i,e no search terms
            return new SearchCorporateResponseModel(new ArrayList<String>(), ServerStatus.SUCCESS, "No companies found");
        }
    }
}
