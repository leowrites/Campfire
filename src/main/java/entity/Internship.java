package entity;

public class Internship {
    private String company;
    private String id;

    public Internship() {
    }

    public Internship(String company, String id) {
        this.company = company;
        this.id = id;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
