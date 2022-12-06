package user.sort;

import entity.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ServerStatus;

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
    ArrayList<Review> reviews;
    ArrayList<Integer> correctlySorted;

    @BeforeEach
    public void setUp() {
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

        correctlySorted = new ArrayList<>(List.of(review3.getId(), review2.getId(), review1.getId(), review4.getId()));
    }

    @Test
    void testHelpfulSort() {
        HelpfulSort sortAlgorithm = new HelpfulSort();
        ArrayList<Review> sortedReviews = sortAlgorithm.sort(reviews);
        ArrayList<Integer> sortedReviewIds = new ArrayList<>();
        for (Review review : sortedReviews) {
            sortedReviewIds.add(review.getId());
        }
        SortResponseModel responseModel = new SortResponseModel(sortedReviewIds, ServerStatus.SUCCESS, "Reviews sorted successfully");
        assertEquals(correctlySorted, responseModel.getSortedOutput());
    }

    @Test
    void testHighestRatingSort() {
        HighestRatingSort sortAlgorithm = new HighestRatingSort();
        ArrayList<Review> sortedReviews = sortAlgorithm.sort(reviews);
        ArrayList<Integer> sortedReviewIds = new ArrayList<>();
        for (Review review : sortedReviews) {
            sortedReviewIds.add(review.getId());
        }
        SortResponseModel responseModel = new SortResponseModel(sortedReviewIds, ServerStatus.SUCCESS, "Reviews sorted successfully");
        assertEquals(correctlySorted, responseModel.getSortedOutput());
    }

    @Test
    void testNewestSort() {
        NewestSort sortAlgorithm = new NewestSort();
        ArrayList<Review> sortedReviews = sortAlgorithm.sort(reviews);
        ArrayList<Integer> sortedReviewIds = new ArrayList<>();
        for (Review review : sortedReviews) {
            sortedReviewIds.add(review.getId());
        }
        SortResponseModel responseModel = new SortResponseModel(sortedReviewIds, ServerStatus.SUCCESS, "Reviews sorted successfully");
        assertEquals(correctlySorted, responseModel.getSortedOutput());
    }
}