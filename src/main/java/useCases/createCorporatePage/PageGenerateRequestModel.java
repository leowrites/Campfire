package useCases.createCorporatePage;

import entity.User;

// The requestModel frames the input data into an object
public class PageGenerateRequestModel {

    private String pageType;
    private String inputLabel;
    private User user;
    private String companyName;
    private String companyInfo;

    // The requestModel uses the number of arguments to distinguish the different types of pages

    public PageGenerateRequestModel(String pageType, String inputLabel, User user){
        this.pageType = pageType;
        this.inputLabel = inputLabel;
        this.user = user;
    }

    // This is requestModel for a corporatePage
    // Overloading
    public PageGenerateRequestModel(String pageType,
                                    String inputLabel, User user, String companyName, String companyInfo){
        this.pageType = pageType;
        this.inputLabel = inputLabel;
        this.user = user;
        this.companyName = companyName;
        this.companyInfo = companyInfo;

    }

    public String getPageType(){
        return this.pageType;
    }

    public String getInputLabel(){
        return this.inputLabel;
    }

    public User getUser(){
        return this.user;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String getCompanyInfo(){
        return this.companyInfo;
    }
}
