package user.sort;
import user.sort.exceptions.SortCriteriaNotFoundException;

public class SortAlgorithmFactory {
    final String sortCriteria;

    public SortAlgorithmFactory(String sortCriteria){
        this.sortCriteria = sortCriteria;
    }

    /**
     * @return a sorting algorithm that
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
