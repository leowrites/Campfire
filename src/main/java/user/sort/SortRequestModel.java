package user.sort;

/** A request model for the sort use case that frames the input data into an object. It holds
 * the sorting criteria, the reviews on an internship, and the corresponding sorting algorithm.
 */
public class SortRequestModel {
    private final String sortCriteria;

    private final int parentInternshipId;

    public SortRequestModel(String sortCriteria, int parentInternshipId){
        this.sortCriteria = sortCriteria;
        this.parentInternshipId = parentInternshipId;
    }

    public String getSortCriteria(){return sortCriteria;}

    public int getParentInternshipId(){return parentInternshipId;}
}
