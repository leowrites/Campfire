package entity;

public class CorporatePage {

    private String companyName;
    private String companyInfo;
    private CorporateRep pageManager;
    private int companyPageId;

    /**
     * Constructs a Corporate page which includes the company name, company info, page manager, and page ID
     * @param companyName is the name of the company
     * @param companyInfo includes info about the company
     * @param pageManager is the corporate representative of the company
     * @param companyPageId is the ID of the company's page
     */
    public CorporatePage(String companyName, String companyInfo, CorporateRep pageManager, int companyPageId){
        this.companyName = companyName;
        this.companyInfo = companyInfo;
        this.pageManager = pageManager;
        this.companyPageId = companyPageId;
    }

    /**
     * returns the company name
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * returns the company info
     * @return the company info
     */
    public String getCompanyInfo() {
        return companyInfo;
    }

    /**
     * returns the corporate representative's info
     * @return the page manager's info
     */
    public CorporateRep getPageManager() {
        return pageManager;
    }

    /**
     * returns the company page's ID
     * @return the company page's ID
     */
    public int getCompanyPageId() {
        return companyPageId;
    }
}
