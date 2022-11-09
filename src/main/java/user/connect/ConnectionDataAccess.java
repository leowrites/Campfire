package user.connect;

import java.io.*;

// remove this dependency once db is implemented
import entity.User;

public class ConnectionDataAccess implements IConnectionDataAccess {
    /**
     * @param userId id of the current user
     * @return A serialized user object
     */
    @Override
    public User getUser(String userId) {
        /**
         * TODO
         *  need to implement this with mySQL query
         *  this is a temporary placeholder
         */
        User user = null;
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream
                    ("fakeUser.txt");
            ObjectInputStream in = new ObjectInputStream
                    (file);
            // Method for deserialization of object
            user = (User)in.readObject();
            in.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * @param user user to be saved
     * @return status of saved user
     */
    @Override
    public boolean saveUser(User user) {
        /**
         * TODO
         *  implement this function
         */
        return false;
    }
}
