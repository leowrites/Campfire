package entity;

public class CorporateRep extends User {
    private String companyName;
    public CorporateRep (String companyName){
        this.companyName = companyName;
    }

    /**
     * returns the company name
     * @return the company name
     */
    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String newName){
        this.companyName = newName;
    }



}
