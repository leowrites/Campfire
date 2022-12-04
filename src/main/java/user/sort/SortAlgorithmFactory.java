package user.sort;
import user.sort.exceptions.SortCriteriaNotFoundException;

/** A class in the sort use case that serves as a factory to create sorting algorithms
 * based on the String sortCriteria variable taken in at initialization.
 */
public class SortAlgorithmFactory {
    final String sortCriteria;

    public SortAlgorithmFactory(String sortCriteria){
        this.sortCriteria = sortCriteria;
    }

    /** Creates a sorting algorithm based on the sortCriteria.
     * @return a sorting algorithm that corresponds to the sortCriteria given
     * @throws SortCriteriaNotFoundException thrown when an invalid sortCriteria is given
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
