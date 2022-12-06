package user.searchcorporate;

import service.ServerStatus;

import java.util.ArrayList;

/**
 * This class represents the response to a corporate account search request.
 * It includes the list of company names of the matching corporate accounts, the status of the request, and a message.
 */
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
