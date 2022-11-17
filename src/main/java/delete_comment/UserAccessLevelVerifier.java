package delete_comment;

import exceptions.NotModeratorException;

import static java.lang.Boolean.FALSE;

public class UserAccessLevelVerifier {

    private final int accessLevel;

    public UserAccessLevelVerifier(int accessLevel){
        this.accessLevel = accessLevel;
    }

    public int getaccesslevel(){
        return this.accessLevel;
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
