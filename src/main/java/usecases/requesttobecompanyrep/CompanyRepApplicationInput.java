package usecases.requesttobecompanyrep;

public class CompanyRepApplicationInput {
    private String username;
    private String companyName;

    public CompanyRepApplicationInput(String username, String companyName) {
        this.username = username;
        this.companyName = companyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
