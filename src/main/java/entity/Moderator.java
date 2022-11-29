package entity;

import java.util.ArrayList;

public class Moderator extends User{
    /**
     * a type of user who can moderate and delete comments and reviews
     * @param id the id of the moderator user
     * @param connectionRequests a list of connection requests to the moderator user
     * @param pendingConnections a list of pending connections to the moderator user
     * @param connections a list of the connections of the moderator user
     * @param username the username of the moderator user
     * @param email the email of the moderator user
     * @param password the password of the moderator user
     * @param name the name of the moderator user
     */
    public Moderator(String id,
                ArrayList<String> connectionRequests,
                ArrayList<String> pendingConnections,
                ArrayList<String> connections,
                String username,
                String email,
                String password,
                String name){
        super.setaccesslevel(1);
    }

}
