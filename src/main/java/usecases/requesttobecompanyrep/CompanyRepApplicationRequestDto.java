package usecases.requesttobecompanyrep;

public class CompanyRepApplicationRequestDto {
    private String username;
    private String companyName;

    public CompanyRepApplicationRequestDto() {
    }

    public CompanyRepApplicationRequestDto(String username, String companyName) {
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
