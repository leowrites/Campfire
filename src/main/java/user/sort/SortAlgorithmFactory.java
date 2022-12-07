package user.sort;
import user.sort.exceptions.SortCriteriaNotFoundException;

/** A class in the sort use case that serves as a factory to create sorting algorithms
 * based on the String sortCriteria variable taken in at initialization.
 */
public class SortAlgorithmFactory {

    /**
     * Creates a sorting algorithm based on the sortCriteria.
     * @param sortCriteria the inputted criteria that dictates what factory to make
     * @return an ISort-implementing sorting algorithm that corresponds to the sortCriteria given
     * @throws SortCriteriaNotFoundException thrown when an invalid sortCriteria is given
     */
    public ISort createSortAlgorithm(String sortCriteria) throws SortCriteriaNotFoundException{
        switch (sortCriteria) {
            case "Helpful":
                return new HelpfulSort();
            case "Newest":
                return new NewestSort();
            case "Highest Rating":
                return new HighestRatingSort();
            default:
                throw new SortCriteriaNotFoundException("That is not an available sort criteria.");
        }
    }
}
