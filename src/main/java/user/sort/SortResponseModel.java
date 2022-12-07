package user.sort;
import java.util.ArrayList;

import service.ServerStatus;

/** A response model for the sort use case that frames the output data into an object. Holds
 * the newly sorted Integer ArrayList of review ids in reviews.
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
