package entity;

import java.util.ArrayList;

/**
 * CorporateRep is a type of user who can create/edit a corporate page
 */

public class CorporateRep extends User {
    private String companyName;
    public CorporateRep (String id, ArrayList<String> connectionRequests, ArrayList<String> pendingConnections,
                         ArrayList<String> connections, String name, String companyName){
        super(id, connectionRequests, pendingConnections, connections, name);
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String newName){
        this.companyName = newName;
    }



}
