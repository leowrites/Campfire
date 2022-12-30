package usecases.createcorporate;

/** A request model for the createcorporate use case that frames the input data into an object.
 * Holds a String representation of the Id of the user who manages the corporate in username, a
 * String of the company's name in companyName, a String of a summary of the corporate intro
 * in companyInfo.
 */
public class CorporateGenerateRequestModel {

    private String username;
    final private String companyName;
    final private String companyInfo;

    public CorporateGenerateRequestModel(String companyName, String companyInfo){
        this.companyName = companyName;
        this.companyInfo = companyInfo;
    }

    public String getUsername(){
        return this.username;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyInfo(){
        return this.companyInfo;
    }
}
