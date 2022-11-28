package user.createCorporate;

import entity.CorporateRep;
import entity.User;

// The requestModel frames the input data into an object
public class CorporateGenerateRequestModel {

    final private CorporateRep owner;
    final private String companyName;
    final private String companyInfo;


    /**
     * This requestModel packs the inputs from user and pass them to the interactor
     * This constructor is used for Corporate objects
     * @param corporateRep is the user who manages the Corporate
     * @param companyName indicates the name of the corporate
     * @param companyInfo has a summary of the corporate intro
     */
    public CorporateGenerateRequestModel(CorporateRep corporateRep, String companyName, String companyInfo){
        this.owner = corporateRep;
        this.companyName = companyName;
        this.companyInfo = companyInfo;

    }

    public User getUser(){
        return this.owner;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String getCompanyInfo(){
        return this.companyInfo;
    }
}
