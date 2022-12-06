package user.sort;
import java.util.ArrayList;

/** A request model for the sort use case that frames the input data into an object. It holds
 * the sorting criteria, the reviews on an internship, and the corresponding sorting algorithm.
 */
public class SortRequestModel {
    private final String sortCriteria;

    private final ArrayList<Integer> reviews;

    private ISort sortingAlgorithm;

    public SortRequestModel(String sortCriteria, ArrayList<Integer> reviews){
        this.sortCriteria = sortCriteria;
        this.reviews = reviews;
    }

    public String getSortCriteria(){return sortCriteria;}

    public ArrayList<Integer> getReviews(){return reviews;}

    public ISort getSortingAlgorithm() {
        return sortingAlgorithm;
    }

    public void setSortingAlgorithm(ISort sortingAlgorithm){
        this.sortingAlgorithm = sortingAlgorithm;
    }
}
