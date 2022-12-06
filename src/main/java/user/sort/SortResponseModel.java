package user.sort;
import java.util.ArrayList;

import org.apache.catalina.Server;
import service.ServerStatus;

/** A response model for the sort use case that frames the output data into an object. It holds
 * the newly sorted ArrayList of reviews.
 */
public class SortResponseModel {

    private final ArrayList<Integer> sortedOutput;

    private final ServerStatus status;

    private final String message;

    public SortResponseModel(ArrayList<Integer> sortedOutput, ServerStatus status, String message){
        this.sortedOutput = sortedOutput;
        this.status = status;
        this.message = message;
    }

    public SortResponseModel(ServerStatus status, String message){
        this.sortedOutput = new ArrayList<>();
        this.status = status;
        this.message = message;
    }

    public ArrayList<Integer> getSortedOutput(){return sortedOutput;}

    public ServerStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
