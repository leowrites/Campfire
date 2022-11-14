package user.requestconnect;

import entity.User;
import user.requestconnect.exceptions.PendingRequestExistsException;
import user.requestconnect.exceptions.UserAlreadyConnectedException;
import user.requestconnect.exceptions.UserNotFoundException;

public class RequestConnectionInteractor implements IRequestConnectionInput {
    private final IRequestConnectionDataAccess dataAccess;
    private final IRequestConnectionSocket socket;

    public RequestConnectionInteractor(IRequestConnectionDataAccess dataAccess,
                                       IRequestConnectionSocket socket) {
        this.dataAccess = dataAccess;
        this.socket = socket;
    }

    /**
     * @param requestModel a request model that contains target
     * @return a response model
     */
    @Override
    public RequestConnectionResponseModel requestConnection(RequestConnectionRequestModel requestModel){
        String userId = requestModel.getUserId();
        String targetId = requestModel.getTargetId();
        User user;
        User target;

        try {
            user = dataAccess.getUser(userId);
            target = dataAccess.getUser(targetId);
            if (user == null || target == null){
                throw new UserNotFoundException("Could not find target user");
            }
        } catch (UserNotFoundException e) {
            return new RequestConnectionResponseModel("Failure %s", e.getMessage());
        }

        RequestConnectionVerifier verifier = new RequestConnectionVerifier(user, target);

        try {
            verifier.verify();
        } catch (UserAlreadyConnectedException | PendingRequestExistsException e) {
            // prepare failure response model for already connected
            return new RequestConnectionResponseModel("Failure %s", e.getMessage());
        }

        RequestConnectionHandler handler = new RequestConnectionHandler(user, target, dataAccess, socket);

        if (verifier.checkIncomingRequest()) {
            handler.acceptConnectionRequest();
        } else {
            handler.sendConnectionRequestToTarget();
        }

        return new RequestConnectionResponseModel("Success");
    }
}
