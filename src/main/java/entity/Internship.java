package entity;
import java.util.List;

public class Internship {
    private int company_id;
    private int id;
    private List<Integer> reviews;
    private String jobTitle;
    private String creator_username;

    // constructor
    public Internship(int company_id, int id, List<Integer> reviews, String jobTitle, String creator_username) {
        this.company_id = company_id;
        this.id = id;
        this.reviews = reviews;
        this.jobTitle = jobTitle;
        this.creator_username = creator_username;
    }
    public Internship() {

    }

    //getter methods
    public int getCompany_id() {
        return company_id;
    }
    public int getId() {
        return id;
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

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
    public void setId(int id) {
        this.id = id;
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