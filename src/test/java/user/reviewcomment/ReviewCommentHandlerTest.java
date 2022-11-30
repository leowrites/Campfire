package user.reviewcomment;

import entity.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.delete_comment.DeleteReviewHandler;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewCommentHandlerTest {

    ArrayList<Review> reviews;

    ArrayList<Review> newReviews;

    String reviewId;

    Review review1;

    Review review2;

    Review review3;

    Review review4;

    @BeforeEach
    public void setup(){
        review1 = new Review("reviewId1", "userId1",
                "This is a test 1", 0);
        review2 = new Review("reviewId2", "userId2",
                "This is a test 2", 0);
        review3 = new Review("reviewId3", "userId3",
                "This is a test 3", 0);
        review4 = new Review("reviewId4", "userId4",
                "This is a test 4", 0);
        reviews = new ArrayList<>();
        newReviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
    }

    @Test
    void testCheckReviewDeleted(){
        reviewId = "reviewId2";
        DeleteReviewHandler deleteReviewHandlerTest = new DeleteReviewHandler(reviewId, reviews);
        deleteReviewHandlerTest.deleteReview();
        newReviews.add(review1);
        newReviews.add(review3);
        newReviews.add(review4);
        assertEquals(deleteReviewHandlerTest.getReviews(), newReviews);
    }
}
