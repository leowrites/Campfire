package user.sort;
import java.util.*;
import entity.Review;

public class SortResponseModel {

    private final ArrayList<Review> sortedOutput;

    public SortResponseModel(ArrayList<Review> sortedOutput){this.sortedOutput = sortedOutput;}

    public ArrayList<Review> getSortedOutput(){return sortedOutput;}
}
