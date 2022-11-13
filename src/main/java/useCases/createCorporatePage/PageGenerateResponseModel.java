package useCases.createCorporatePage;

public class PageGenerateResponseModel {
    String resultMessage;
    String companyName = null;
    String companyInfo = null;

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

}