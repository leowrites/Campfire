package user.createCorporatePage;

import entity.User;

public class PageGenerateDsRequestModel {
    private final String pageLabel;
    private User owner;
    private String companyName;
    private String companyInfo;


    public PageGenerateDsRequestModel(String pageLabel, User owner) {
        this.pageLabel = pageLabel;
        this.owner = owner;
    }

    // This is DsRequestModel for a corporatePage
    // Overloading
    public PageGenerateDsRequestModel(String pageLabel, User user, String companyName, String companyInfo) {
        this.pageLabel = pageLabel;
        this.owner = user;
        this.companyName = companyName;
        this.companyInfo = companyInfo;

    }



}
