package user.sort;
import java.util.*;
import entity.Review;

public class SortRequestModel {
    private final String sortCriteria;

    private final ArrayList<Review> reviews;

    private ISort sortingAlgorithm;

    public SortRequestModel(String sortCriteria, ArrayList<Review> reviews){
        this.sortCriteria = sortCriteria;
        this.reviews = reviews;
    }

    public String getSortCriteria(){return sortCriteria;}

    public ArrayList<Review> getReviews(){return reviews;}

    public ISort getSortingAlgorithm() {
        return sortingAlgorithm;
    }

    public void setSortingAlgorithm(ISort sortingAlgorithm){
        this.sortingAlgorithm = sortingAlgorithm;
    }
}
