package entity;
import java.util.ArrayList;

public class Corporate {
    private User rep;
    private String companyName;
    private String companyInfo;
    private ArrayList<Integer> internships;

    /**
     *
     * @param rep is the CorporateRep user who manages the corporatePage
     * @param companyName is the name of the corporate, and the identifier of the corporate object
     * @param companyInfo is the background information displayed on the corporatePage, it can
     *                    be a summary of the company or the recruitment timeline.
     */
    public Corporate(User rep,
                     String companyName, String companyInfo){
        this.rep = rep;
        this.companyName = companyName;
        this.companyInfo = companyInfo;
        this.internships = new ArrayList<>();
    }

    public User getRep(){ return this.rep; }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getCompanyInfo() {
        return this.companyInfo;
    }

    public ArrayList<Integer> getInternships() { return this.internships; }

    public void setOwner(User rep){this.rep = rep;}

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setCompanyInfo(String companyInfo){
        this.companyInfo = companyInfo;
    }
    public void addInternship(Integer internshipId) { this.internships.add(internshipId); }

}
