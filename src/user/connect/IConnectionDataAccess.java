package user.connect;
import entity.User;

public interface IConnectionDataAccess {
    // returns a serialized user object?
    User getUser();
    Boolean isTargetConnected();
    Boolean isPendingRequest();
}
