package service;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import entity.User;

import java.util.List;

public class AuthenticatedUserDto {
    private String username;

    private String email;
    private String name;
    @JsonIncludeProperties("username")
    private List<User> connectedUsers;

    @JsonIncludeProperties("username")
    private List<User> incomingConnectionRequests;
    @JsonIncludeProperties("username")
    private List<User> outgoingConnectionRequests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setIncomingConnectionRequests(List<User> incomingConnectionRequests) {
        this.incomingConnectionRequests = incomingConnectionRequests;
    }

    public List<User> getOutgoingConnectionRequests() {
        return outgoingConnectionRequests;
    }

    public void setOutgoingConnectionRequests(List<User> outgoingConnectionRequests) {
        this.outgoingConnectionRequests = outgoingConnectionRequests;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
