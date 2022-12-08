package usecases.sort;
import usecases.sort.exceptions.SortCriteriaNotFoundException;

/** A factory that creates sorting algorithms that commonly implement ISort, based on
 * the sortCriteria variable that is taken in when this SortAlgorithmFactory is constructed.
 */
public class SortAlgorithmFactory {
    final String sortCriteria;

    public SortAlgorithmFactory(String sortCriteria){
        this.sortCriteria = sortCriteria;
    }

    /** Creates a sorting algorithm based on sortCriteria.
     * @return a sorting algorithm that corresponds to the sortCriteria given.
     * @throws SortCriteriaNotFoundException thrown when an invalid sortCriteria is given.
     */
    public ISort createSortAlgorithm() throws SortCriteriaNotFoundException{
        switch (sortCriteria) {
            case "Helpful":
                return new HelpfulSort();
            case "Newest":
                return new NewestSort();
            case "Highest Rating":
                return new HighestRatingSort();
        }
        throw new SortCriteriaNotFoundException("That is not an available sort criteria.");
    }
}
