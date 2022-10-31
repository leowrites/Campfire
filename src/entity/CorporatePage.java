package entity;

public class CorporatePage {

    private String companyName;
    private String companyInfo;
    private CorporateRepresentative pageManager;
    private int companyPageId;

    public CorporatePage(String companyName, String companyInfo, CorporateRepresentative pageManager, int companyPageId){
        this.companyName = companyName;
        this.companyInfo = companyInfo;
        this.pageManager = pageManager;
        this.companyPageId = companyPageId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public CorporateRepresentative getPageManager() {
        return pageManager;
    }

    public int getCompanyPageId() {
        return companyPageId;
    }
}
