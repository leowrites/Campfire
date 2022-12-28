package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @Column(name="username")
    private String username;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "FK_USERNAME"))
    @JsonIgnoreProperties({"connectedUsers", "incomingConnectionRequests", "outgoingConnectionRequests"})
    private List<User> connectedUsers;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "FK_USERNAME"))
    @JsonIgnoreProperties({"connectedUsers", "incomingConnectionRequests", "outgoingConnectionRequests"})
    private List<User> incomingConnectionRequests;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "FK_USERNAME"))
    @JsonIgnoreProperties({"connectedUsers", "incomingConnectionRequests", "outgoingConnectionRequests"})
    private List<User> outgoingConnectionRequests;
    @Column
    private String name;
    @Column(length = 200)
    private String email;
    @Column
    @JsonIgnore
    private String password;

    @ManyToMany

    private Collection<Role> role;

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

    public Collection<Role> getRole() {
        return role;
    }

    public void setRole(Collection<Role> role) {
        this.role = role;
    }
}
