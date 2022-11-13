package useCases.createCorporatePage;

import entity.User;

public class PageGenerateDsRequestModel {
    private final String pageLabel;
    private User owner;

    public PageGenerateDsRequestModel(String pageLabel, User owner) {
        this.pageLabel = pageLabel;
        this.owner = owner;
    }
}
