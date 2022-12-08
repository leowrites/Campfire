package usecases.deletecomment;

import usecases.exceptions.NotEnoughAccessLevelException;

/** A class in the deletecomment use case that verifies if a user has enough access level.
 */
public class AccessLevelVerifier {

    private final int accessLevel;

    public AccessLevelVerifier(int accessLevel){
        this.accessLevel = accessLevel;
    }

    /** Verifies if the user has enough access level.
     * @throws NotEnoughAccessLevelException thrown when the user does not have enough access level.
     */
    public void verify() throws NotEnoughAccessLevelException {
        if (this.accessLevel != 1){
            throw new NotEnoughAccessLevelException("Not enough access level");
            }
        }
    }
