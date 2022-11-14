package entity;

import java.util.ArrayList;

public class Moderator extends User{

    public Moderator(String id,
                ArrayList<String> connectionRequests,
                ArrayList<String> pendingConnections,
                ArrayList<String> connections,
                String username,
                String email,
                String password,
                String name){
        this.setaccesslevel();
    }

}
