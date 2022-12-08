package usecases.sort.exceptions;

/** An exception that is thrown when the sort criteria given is not a valid criteria.
 */
public class SortCriteriaNotFoundException extends Exception{

    public SortCriteriaNotFoundException(String msg){
        super(msg);
    }
}
