package user.sort;
import java.util.*;
import entity.Review;

public interface ISort {
    SortResponseModel sort(ArrayList<Review> reviews);
}
