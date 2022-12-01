package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<String> incomingConnectionRequests;
    private ArrayList<String> outgoingConnectionRequests;
    private ArrayList<String> connections;
    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private int accesslevel;
    public User() {}
    public User(String id,
                ArrayList<String> incomingConnectionRequests,
                ArrayList<String> outgoingConnectionRequests,
                ArrayList<String> connections,
                String username,
                String email,
                String password,
                String name) {
        this.incomingConnectionRequests = incomingConnectionRequests;
        this.outgoingConnectionRequests = outgoingConnectionRequests;
        this.connections = connections;
        this.username = username;
        this.email = email;
        this.id = id;
        this.password = password;
        this.name = name;
        this.accesslevel = 1;
    }

    public User(String id,
                ArrayList<String> incomingConnectionRequests,
                ArrayList<String> outgoingConnectionRequests,
                ArrayList<String> connections,
                String username,
                String email,
                String password,
                String name,
                int accesslevel) {
        this.incomingConnectionRequests = incomingConnectionRequests;
        this.outgoingConnectionRequests = outgoingConnectionRequests;
        this.connections = connections;
        this.username = username;
        this.email = email;
        this.id = id;
        this.password = password;
        this.name = name;
        this.accesslevel = accesslevel;
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
    public String getId(){
        return id;
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
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getaccesslevel(){return this.accesslevel;}

    public void setaccesslevel(int accesslevel){
        this.accesslevel = accesslevel;
    }
}
