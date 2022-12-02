package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<String> incomingConnectionRequests;
    private ArrayList<String> outgoingConnectionRequests;
    private ArrayList<String> connections;
    private String name;
    private String username;
    private String email;
    private String password;
    private int accessLevel;
    public User() {}

    public User(
                String username,
                String email,
                String password,
                String name) {
        this.incomingConnectionRequests = new ArrayList<>();
        this.outgoingConnectionRequests = new ArrayList<>();
        this.connections = new ArrayList<>();
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.accessLevel = 0;
    }

    public ArrayList<String> getIncomingConnectionRequests() {
        return incomingConnectionRequests;
    }

    public void setIncomingConnectionRequests(ArrayList<String> incomingConnectionRequests) {
        this.incomingConnectionRequests = incomingConnectionRequests;
    }

    public ArrayList<String> getOutgoingConnectionRequests() {
        return outgoingConnectionRequests;
    }

    public void setOutgoingConnectionRequests(ArrayList<String> outgoingConnectionRequests) {
        this.outgoingConnectionRequests = outgoingConnectionRequests;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<String> connections) {
        this.connections = connections;
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
}
