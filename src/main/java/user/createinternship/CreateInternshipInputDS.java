package user.createinternship;

/** A request model for the createinternship use case that frames the input data into an object.
 * Holds a String of the job title of the internship, an int representation of the id of the
 * company giving the internship, and a String representation of the username of the user who
 * created the internship.
 */
public class CreateInternshipInputDS {
    private String jobTitle;
    private int companyID;
    private String creatorUsername;

    public CreateInternshipInputDS(String jobTitle, int companyID, String creatorUsername) {
        this.jobTitle = jobTitle;
        this.companyID = companyID;
        this.creatorUsername = creatorUsername;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

}
