package usecases.createinternship;

import java.util.UUID;

/** A request model for the createinternship use case that frames the input data into an object.
 * Holds a String of the job title of the internship, an int representation of the id of the
 * company giving the internship, and a String representation of the username of the user who
 * created the internship.
 */
public class CreateInternshipInputDS {
    private final String jobTitle;
    private final UUID companyID;
    private final String creatorUsername;

    public CreateInternshipInputDS(String jobTitle, UUID companyID, String creatorUsername) {
        this.jobTitle = jobTitle;
        this.companyID = companyID;
        this.creatorUsername = creatorUsername;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public UUID getCompanyID() {
        return companyID;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

}
