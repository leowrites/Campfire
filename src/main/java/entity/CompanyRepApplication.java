package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class CompanyRepApplication {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    @NotBlank(message = "User must not be null!")
    private User user;
    @OneToOne
    @NotBlank(message = "Company must not be null!")
    private Corporate corporate;

    public CompanyRepApplication(){};

    public CompanyRepApplication(UUID id, User user, Corporate corporate) {
        this.id = id;
        this.user = user;
        this.corporate = corporate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Corporate getCorporate() {
        return corporate;
    }

    public void setCorporate(Corporate corporate) {
        this.corporate = corporate;
    }
}
