package entity;
import java.util.ArrayList;

/**
 * CorporatePage is a child class of the abstract class Page, which displays company info and internship reviews
 * Only the CorporateRep user has access to create a CorporatePage instance
 */

public class CorporatePage extends Page {
    private String companyName;
    private String companyInfo;

    // the ArrayList of internships contains the internships that are displayed on the page
    // they are not necessarily added when the corporate page is created
    // they can be added by the student users who write reviews about their internships
    private ArrayList<Internship> internships = new ArrayList<Internship>();

    /**
     *
     * @param pageLabel is the identifier of the corporatePage
     * @param owner is the CorporateRep user who manages the corporatePage
     * @param companyName is the name the corporate
     * @param companyInfo is the background information displayed on the corporatePage, it can
     *                    be a summary of the company or the recruitment timeline.
     */
    public CorporatePage(String pageLabel, CorporateRep owner,
                         String companyName, String companyInfo){
        super(pageLabel, owner);
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

    public ArrayList<Internship> getInternships() { return this.internships; }

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
