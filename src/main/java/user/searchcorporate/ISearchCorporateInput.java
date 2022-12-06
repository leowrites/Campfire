package user.searchcorporate;

/**
 * This interface defines methods for interacting with corporate accounts in a data store.
 * It allows retrieving, saving, and updating corporate account data, as well as checking if a company exists.
 */
public interface ISearchCorporateInput {

    public SearchCorporateResponseModel search(SearchCorporateRequestModel requestModel);

}
