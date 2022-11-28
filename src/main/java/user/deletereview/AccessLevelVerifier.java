package user.deletereview;

import exceptions.NotEnoughAccessLevelException;

public class AccessLevelVerifier {

    private final int accessLevel;

    public AccessLevelVerifier(int accessLevel){
        this.accessLevel = accessLevel;
    }

    /**
     * raises error if the user does not have access level
     */
    public void verify() throws NotEnoughAccessLevelException {
        if (this.accessLevel != 1){
            throw new NotEnoughAccessLevelException("Not enough access level");
            }
        }
    }
