package usecases.sort;

import entity.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SortAlgorithmsTest {

    Review review1;
    Review review2;
    Review review3;
    Review review4;
    Review review5;
    ArrayList<Review> reviews;
    ArrayList<Review> correctlySorted;

    @BeforeEach
    public void setUp(){
        review1 = new Review("user1", "", 3);
        review1.setNumDislikes(3);
        review1.setNumLikes(3);
        review1.setDatePosted(new Date(2022, Calendar.JUNE, 24));
        review2 = new Review("user2", "", 5);
        review2.setNumDislikes(5);
        review2.setNumLikes(5);
        review2.setDatePosted(new Date(2022, Calendar.JULY, 24));
        review3 = new Review("user4", "", 14);
        review3.setNumDislikes(115);
        review3.setNumLikes(115);
        review3.setDatePosted(new Date(2022, Calendar.SEPTEMBER, 24));
        review4 = new Review("user5", "", -3);
        review4.setNumDislikes(10);
        review4.setNumLikes(0);
        review4.setDatePosted(new Date(2022, Calendar.MARCH, 24));
        reviews = new ArrayList<>(List.of(review1, review2, review3, review4));

        correctlySorted = new ArrayList<>(List.of(review3, review2, review1, review4));
    }

    @Test
    void testHelpfulSort(){
        HelpfulSort sortAlgorithm = new HelpfulSort();
        SortResponseModel responseModel = sortAlgorithm.sort(reviews);
        assertEquals(correctlySorted, responseModel.getSortedOutput());
    }

    @Test
    void testHighestRatingSort(){
        HighestRatingSort sortAlgorithm = new HighestRatingSort();
        SortResponseModel responseModel = sortAlgorithm.sort(reviews);
        assertEquals(correctlySorted, responseModel.getSortedOutput());
    }

    @Test
    void testNewestSort(){
        NewestSort sortAlgorithm = new NewestSort();
        SortResponseModel responseModel = sortAlgorithm.sort(reviews);
        assertEquals(correctlySorted, responseModel.getSortedOutput());
    }

    @Test
    void testSortTwoValuesEqualCase(){
        review5 = new Review("user3", "", 3);
        review5.setNumDislikes(3);
        review5.setNumLikes(3);
        review5.setDatePosted(new Date(2022, Calendar.JUNE, 24));
        reviews.add(review5);
        ArrayList<Review> reviews1 = new ArrayList<>(reviews);
        ArrayList<Review> reviews2 = new ArrayList<>(reviews);
        ArrayList<Review> reviews3 = new ArrayList<>(reviews);

        ArrayList<Integer> correctlySortedHelpful = new ArrayList<>(List.of(115, 5, 3, 3, 0));
        ArrayList<Integer> correctlySortedHighestRating = new ArrayList<>(List.of(10, 5, 3, 3, 0));
        ArrayList<Date> correctlySortedNewest = new ArrayList<>(List.of(new Date(2022, Calendar.SEPTEMBER, 24),
                new Date(2022, Calendar.JULY, 24), new Date(2022, Calendar.JUNE, 24),
                new Date(2022, Calendar.JUNE, 24), new Date(2022, Calendar.MARCH, 24)));

        SortResponseModel helpfulResponse = new HelpfulSort().sort(reviews1);
        SortResponseModel highestRatingResponse = new HighestRatingSort().sort(reviews2);
        SortResponseModel newestResponse = new NewestSort().sort(reviews3);

        ArrayList<Integer> sortedValuesHelpful = new ArrayList<>();
        ArrayList<Integer> sortedValuesHighestRating = new ArrayList<>();
        ArrayList<Date> sortedValuesNewest = new ArrayList<>();

        for (Review review : helpfulResponse.getSortedOutput()){
            sortedValuesHelpful.add(review.getNumLikes());
        }
        for (Review review : highestRatingResponse.getSortedOutput()){
            sortedValuesHighestRating.add(review.getRating());
        }
        for (Review review : newestResponse.getSortedOutput()){
            sortedValuesNewest.add(review.getDatePosted());
        }

        assertEquals(correctlySortedHelpful, sortedValuesHelpful);
        assertEquals(correctlySortedHighestRating, sortedValuesHighestRating);
        assertEquals(correctlySortedNewest, sortedValuesNewest);
    }
}
