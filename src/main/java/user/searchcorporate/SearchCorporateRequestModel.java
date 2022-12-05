package user.searchcorporate;

public class SearchCorporateRequestModel {
    private final String searchterm;

    public String getSearchterm() {
        return searchterm;
    }

    public SearchCorporateRequestModel(String searchterm) {
        this.searchterm = searchterm;
    }
}
