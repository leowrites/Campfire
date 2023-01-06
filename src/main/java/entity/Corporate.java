package entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="corporates")
public class Corporate {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne(cascade = {CascadeType.ALL})
    private User rep;
    @Column(unique = true)
    private String companyName;
    @Column
    private String companyInfo;
    @OneToMany
    private List<Internship> internships;

    public Corporate(User rep, String companyName, String companyInfo){
        this.rep = rep;
        this.companyName = companyName;
        this.companyInfo = companyInfo;
        this.internships = new ArrayList<>();
    }

    public Corporate() {}

    public UUID getId() {
        return id;
    }

    public User getRep(){ return this.rep; }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getCompanyInfo() {
        return this.companyInfo;
    }

    public List<Internship> getInternships() { return this.internships; }

    public void setRep(User rep){this.rep = rep;}

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setCompanyInfo(String companyInfo){
        this.companyInfo = companyInfo;
    }
    public void addInternship(Internship internship) { this.internships.add(internship); }

}
