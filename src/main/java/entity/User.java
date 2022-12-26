package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    @Column(name="username")
    private String username;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "FK_USERNAME"))
    private List<User> connectedUsers;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "FK_USERNAME"))
    private List<User> incomingConnectionRequests;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "FK_USERNAME"))
    private List<User> outgoingConnectionRequests;
    @Column
    private String name;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private int accessLevel;
    @Column
    private boolean corporateRep;

    public User() {}

    public User(
                String username,
                String email,
                String password,
                String name) {
        this.incomingConnectionRequests = new ArrayList<>();
        this.outgoingConnectionRequests = new ArrayList<>();
        this.connectedUsers = new ArrayList<>();
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.accessLevel = 0;
        this.corporateRep = false;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public int getAccessLevel(){return this.accessLevel;}

    public void setAccessLevel(int accessLevel){
        this.accessLevel = accessLevel;
    }

    public boolean getCorporateRep() {
        return this.corporateRep;
    }

    public void setCorporateRep(boolean corporateRep) {
        this.corporateRep = corporateRep;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCorporateRep() {
        return corporateRep;
    }

    public List<User> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(List<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public List<User> getIncomingConnectionRequests() {
        return incomingConnectionRequests;
    }

    public void setIncomingConnectionRequests(List<User> incomingConnectionRequestUsers) {
        this.incomingConnectionRequests = incomingConnectionRequestUsers;
    }

    public List<User> getOutgoingConnectionRequests() {
        return outgoingConnectionRequests;
    }

    public void setOutgoingConnectionRequests(List<User> outgoingConnectionRequestUsers) {
        this.outgoingConnectionRequests = outgoingConnectionRequestUsers;
    }
}
