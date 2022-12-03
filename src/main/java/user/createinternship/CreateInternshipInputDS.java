package user.createinternship;

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
