package entity;

import java.util.ArrayList;

/**
 * CorporateRep is a type of user who can create/edit a corporate page
 */

public class CorporateRep extends User {
    private String companyName;
    public CorporateRep (String id,
                         ArrayList<String> incomingConnectionRequests,
                         ArrayList<String> outgoingConnectionRequests,
                         ArrayList<String> connections,
                         String username,
                         String email,
                         String password,
                         String name, String companyName){
        super(id, incomingConnectionRequests, outgoingConnectionRequests, connections,
                username, email, password, name);
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String newName){
        this.companyName = newName;
    }



}
