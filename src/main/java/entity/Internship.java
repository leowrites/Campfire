package entity;
import java.util.List;

public class Internship {
    private int companyID;
    private List<Integer> reviews;
    private String jobTitle;
    private String creator_username;
    private int id;

    // constructor
    public Internship(int companyID, List<Integer> reviews, String jobTitle, String creator_username) {
        this.companyID = companyID;
        this.reviews = reviews;
        this.jobTitle = jobTitle;
        this.creator_username = creator_username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //getter methods
    public int getCompanyID() {
        return companyID;
    }
    public List<Integer> getReviews() {
        return reviews;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public String getCreator_username() {
        return creator_username;
    }

    //setter methods

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
    public void setReviews(List<Integer> reviews) {
        this.reviews = reviews;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public void setCreator_username(String creator_username) {
        this.creator_username = creator_username;
    }
}