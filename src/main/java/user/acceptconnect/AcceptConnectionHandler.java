package user.acceptconnect;

import entity.User;
import service.IUserDataAccess;
import user.acceptconnect.exceptions.NoRequestFoundException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import java.util.ArrayList;

public class AcceptConnectionHandler {
    private final User user;
    private final User target;
    private final IUserDataAccess dataAccess;
    public AcceptConnectionHandler(User user, User target, IUserDataAccess dataAccess) {
        this.user = user;
        this.target = target;
        this.dataAccess = dataAccess;
    }

    public void acceptConnection() throws UserAlreadyConnectedException, NoRequestFoundException {
        if (user.getConnections().contains(target.getUsername())) {
            // throw already connected error
            throw new UserAlreadyConnectedException(String.format("%s and %s are already connected!",
                    user.getUsername(), target.getUsername()));
        }
        if (!user.getConnectionRequests().contains(target.getUsername())) {
            // throw no request from user
            throw new NoRequestFoundException(String.format("%s and %s are already connected!",
                    user.getUsername(), target.getUsername()));
        }
        ArrayList<String> userConnectionRequests = user.getConnectionRequests();
        userConnectionRequests.remove(target.getUsername());
        user.setConnectionRequests(userConnectionRequests);

        ArrayList<String> userConnections = user.getConnections();
        userConnections.add(target.getUsername());
        user.setConnectionRequests(userConnections);

        ArrayList<String> targetPendingConnections = target.getPendingConnections();
        targetPendingConnections.remove(target.getUsername());
        target.setPendingConnections(targetPendingConnections);

        ArrayList<String> targetConnections = target.getConnections();
        targetConnections.add(user.getUsername());
        target.setConnectionRequests(targetConnections);

        dataAccess.updateUser(user);
        dataAccess.updateUser(target);
    }
}
