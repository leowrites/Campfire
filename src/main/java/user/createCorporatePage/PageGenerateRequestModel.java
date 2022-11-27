package user.createCorporatePage;

import entity.CorporateRep;
import entity.User;

// The requestModel frames the input data into an object
public class PageGenerateRequestModel {

    final private PageType pageType;
    final private String inputLabel;
    final private User user;
    final private String companyName;
    final private String companyInfo;


    /**
     * This requestModel packs the inputs from user and pass them to the interactor
     * This constructor is used for CorporatePage objects
     * @param pageType indicates the type of the page. For this constructor, it will be CORPORATE
     * @param inputLabel indicates the pageLabel inputted by the user
     * @param corporateRep is the user who manages the CorporatePage
     * @param companyName indicates the name of the corporate
     * @param companyInfo has a summary of the corporate intro
     */
    public PageGenerateRequestModel(PageType pageType,
                                    String inputLabel, CorporateRep corporateRep, String companyName, String companyInfo){
        this.pageType = pageType;
        this.inputLabel = inputLabel;
        this.user = corporateRep;
        this.companyName = companyName;
        this.companyInfo = companyInfo;

    }

    public PageType getPageType(){
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
