package entity;

public class CorporatePage {

    private String companyName;
    private String companyInfo;
    private CorporateRep pageManager;
    private int companyPageId;

    public CorporatePage(String companyName, String companyInfo, CorporateRep pageManager, int companyPageId){
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

    public CorporateRep getPageManager() {
        return pageManager;
    }

    public int getCompanyPageId() {
        return companyPageId;
    }
}
