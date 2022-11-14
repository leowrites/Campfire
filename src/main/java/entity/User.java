package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<String> connectionRequests;
    private ArrayList<String> pendingConnections;
    private ArrayList<String> connections;
    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    public User() {}
    public User(String id,
                ArrayList<String> connectionRequests,
                ArrayList<String> pendingConnections,
                ArrayList<String> connections,
                String username,
                String email,
                String password,
                String name) {
        this.connectionRequests = connectionRequests;
        this.pendingConnections = pendingConnections;
        this.connections = connections;
        this.username = username;
        this.email = email;
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public ArrayList<String> getConnectionRequests() {
        return connectionRequests;
    }

    public void setConnectionRequests(ArrayList<String> connectionRequests) {
        this.connectionRequests = connectionRequests;
    }

    public ArrayList<String> getPendingConnections() {
        return pendingConnections;
    }

    public void setPendingConnections(ArrayList<String> pendingConnections) {
        this.pendingConnections = pendingConnections;
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
}
