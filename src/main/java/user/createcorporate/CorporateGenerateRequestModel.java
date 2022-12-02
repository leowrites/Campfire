package user.createcorporate;

public class CorporateGenerateRequestModel {

    final private String userId;
    final private String companyName;
    final private String companyInfo;


    /**
     * This requestModel packs the inputs from user and pass them to the interactor
     * This constructor is used for Corporate objects
     * @param userId is the ID of the user who manages the Corporate
     * @param companyName indicates the name of the corporate
     * @param companyInfo has a summary of the corporate intro
     */
    public CorporateGenerateRequestModel(String userId, String companyName, String companyInfo){
        this.userId = userId;
        this.companyName = companyName;
        this.companyInfo = companyInfo;

    }

    public String getUserId(){
        return this.userId;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String getCompanyInfo(){
        return this.companyInfo;
    }
}
