package user.searchcorporate;

/**
 * This class represents the data for a corporate account search request.
 * It includes the search term that will be used to find matching corporate accounts.
 */
public class SearchCorporateRequestModel {
    private final String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public SearchCorporateRequestModel() {
        this.searchTerm = null;
    }

    public SearchCorporateRequestModel(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
