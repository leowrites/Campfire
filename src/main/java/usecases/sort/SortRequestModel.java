package usecases.sort;

/** A request model for the sort use case that frames the input data into an object. Holds
 * the String sorting criteria in sortCriteria, and the int representation of the id of the parent
 * internship in parentInternshipId
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
