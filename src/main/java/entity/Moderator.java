package entity;

import java.util.ArrayList;

public class Moderator extends User{

    public Moderator(String id,
                     ArrayList<String> connectionRequests,
                     ArrayList<String> pendingConnections,
                     ArrayList<String> connections,
                     String name,
                     int access_level){
        super(id, connectionRequests, pendingConnections, connections, name, 1);
    }

}
