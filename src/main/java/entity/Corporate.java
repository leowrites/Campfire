package entity;
import java.util.ArrayList;

/**
 * Corporate is a child class of the abstract class Page, which displays company info and internship reviews
 * Only the CorporateRep user has access to create a Corporate instance
 */

public class Corporate {
    private CorporateRep owner;
    private String companyName;
    private String companyInfo;

    // the ArrayList of internships contains the internships that are displayed on the page
    // they are not necessarily added when the corporate page is created
    // they can be added by the student users who write reviews about their internships
    private ArrayList<Internship> internships = new ArrayList<Internship>();

    /**
     *
     * @param owner is the CorporateRep user who manages the corporatePage
     * @param companyName is the name of the corporate, and the identifier of the corporate object
     * @param companyInfo is the background information displayed on the corporatePage, it can
     *                    be a summary of the company or the recruitment timeline.
     */
    public Corporate(CorporateRep owner,
                     String companyName, String companyInfo){
        this.owner = owner;
        this.companyName = companyName;
        this.companyInfo = companyInfo;
    }

    public CorporateRep getOwner(){ return owner; }


    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public ArrayList<Internship> getInternships() { return this.internships; }

    public void setOwner(CorporateRep owner){this.owner = owner;}

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setCompanyInfo(String companyInfo){
        this.companyInfo = companyInfo;
    }


    /**
     *
     * @param internship: an internship object can be added to the internships ArrayList
     *                  using the addInternship method
     */
    public void addInternship(Internship internship) { this.internships.add(internship); }

}
