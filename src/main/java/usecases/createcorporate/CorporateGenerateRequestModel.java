package usecases.createcorporate;

/** A request model for the createcorporate use case that frames the input data into an object.
 * Holds a String representation of the Id of the user who manages the corporate in userId, a
 * String of the company's name in companyName, a String of a summary of the corporate intro
 * in companyInfo.
 */
public class CorporateGenerateRequestModel {

    final private String userId;
    final private String companyName;
    final private String companyInfo;

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
