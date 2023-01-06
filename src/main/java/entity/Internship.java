package entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="internships")
public class Internship {
    @Column
    private UUID companyId;
    @OneToMany
    private List<Review> reviews;
    @Column
    private String jobTitle;
    @Column
    private String creator;
    @Id
    @GeneratedValue
    private UUID id;

    // constructor
    public Internship(UUID companyId, String jobTitle, String creator_username) {
        this.companyId = companyId;
        this.reviews = new ArrayList<>();
        this.jobTitle = jobTitle;
        this.creator = creator_username;
    }

    public Internship() {}

    public UUID getId() {
        return id;
    }

    //getter methods
    public UUID getCompanyId() {
        return companyId;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public String getCreator() {
        return creator;
    }

    //setter methods

    public void setCompanyId(UUID companyID) {
        this.companyId = companyID;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public void setCreator(String creator_username) {
        this.creator = creator_username;
    }
}