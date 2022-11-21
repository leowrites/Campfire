package user.createCorporatePage;

public class PageGenerateResponseModel {
    private String resultMessage;
    private String companyName = null;
    private String companyInfo = null;

    // The responseModel constructor uses the number of arguments to distinguish the different types of pages

    public PageGenerateResponseModel(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    // This is responseModel for a corporatePage
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