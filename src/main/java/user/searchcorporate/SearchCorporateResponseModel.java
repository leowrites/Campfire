package user.searchcorporate;

import service.ServerStatus;

import java.util.ArrayList;

public class SearchCorporateResponseModel {
    private final ArrayList<String> list_of_companies;
    private final ServerStatus status;
    private final String message;

    public ArrayList<String> getList_of_companies() {
        return list_of_companies;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public SearchCorporateResponseModel(ArrayList<String> list_of_companies, ServerStatus status, String message) {
        this.list_of_companies = list_of_companies;
        this.status = status;
        this.message = message;
    }
}
