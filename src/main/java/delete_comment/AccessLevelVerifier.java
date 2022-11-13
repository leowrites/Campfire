package delete_comment;

import exceptions.NotModeratorException;

public class AccessLevelVerifier {

    private final int access_level;

    public AccessLevelVerifier(int access_level){
        this.access_level = access_level;
    }

    public int getaccesslevel(){
        return this.access_level;
    }

    /**
     * raises error if the user does not have access level
     */
    public void verify() throws NotModeratorException{
        if (this.getaccesslevel() != 1){
            throw new NotModeratorException("Not enough access level");
            }
        }
    }
