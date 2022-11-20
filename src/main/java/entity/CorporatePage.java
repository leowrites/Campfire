package entity;
import java.util.ArrayList;

/**
 * CorporatePage is a child class of the abstract class Page, which displays company info and internship reviews
 * Only the CorporateRep user has access to create a CorporatePage instance
 */

public class CorporatePage extends Page {

    // This uniqueIdTracker starts from 0, and increments by 1 every time it's assigned to a CorporatePage instance.

    private String companyName;
    private String companyInfo;
    private CorporateRep pageManager;
    private int companyPageId;
    //private ArrayList<Internship> internshipsArray = new ArrayList<Internship>();

    public CorporatePage(String pageName, User owner,
                         String companyName, String companyInfo){
        super(pageName, owner);
        this.companyName = companyName;
        this.companyInfo = companyInfo;
    }

    public CorporatePage(String pageName, User owner){
        super(pageName, owner);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    //public ArrayList<Internship> getInternshipsArray() { return this.internshipsArray; }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setCompanyInfo(String companyInfo){
        this.companyInfo = companyInfo;
    }

    //public void addInternship(Internship internship) { this.internshipsArray.add(internship); }


}
