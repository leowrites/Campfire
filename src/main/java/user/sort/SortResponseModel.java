package user.sort;
import java.util.ArrayList;

/** A response model for the sort use case that frames the output data into an object. It holds
 * the newly sorted ArrayList of reviews.
 */
public class SortResponseModel {

    private final ArrayList<Integer> sortedOutput;

    public SortResponseModel(ArrayList<Integer> sortedOutput){this.sortedOutput = sortedOutput;}

    public ArrayList<Integer> getSortedOutput(){return sortedOutput;}
}
