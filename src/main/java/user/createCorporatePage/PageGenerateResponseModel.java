package user.createCorporatePage;

public class PageGenerateResponseModel {
    private String resultMessage;
    private String companyName = null;
    private String companyInfo = null;

    /**
     * This responseModel is used when page creation is failed
     * @param resultMessage is passed in as the failure result message
     */

    public PageGenerateResponseModel(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * This response model is used when page is successfully created
     * @param resultMessage is passed in as the success result message
     * @param companyName is passed to the database
     * @param companyInfo is passed to the database
     */
    public PageGenerateResponseModel(String resultMessage, String companyName, String companyInfo){
        this.resultMessage = resultMessage;
        this.companyName = companyName;
        this.companyInfo = companyInfo;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String getCompanyInfo(){
        return this.companyInfo;
    }

    public void setResultMessage(String resultMessage) { this.resultMessage = resultMessage; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setCompanyInfo(String companyInfo) { this.companyInfo = companyInfo; }
}