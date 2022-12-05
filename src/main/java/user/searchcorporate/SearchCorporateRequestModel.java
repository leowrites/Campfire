package user.searchcorporate;

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
