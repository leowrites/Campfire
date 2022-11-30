package user.reviewcomment;

import entity.Review;
import exceptions.NotOwnReviewException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.deletereview.OwnerVerifierReview;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewOwnerVerifierTest {

    Review testReview;

    String testUserId;

    @BeforeEach
    public void setup(){
        testUserId = "userId1";
        testReview = new Review("reviewId1", "userid2", "Apple Corp",
                "This is a test", 0);
    }

    @Test
    public void testReviewOwnerVerifier(){
        OwnerVerifierReview ownerVerifierReviewTest = new OwnerVerifierReview(testReview, testUserId);
        Throwable exception = assertThrows(NotOwnReviewException.class, ownerVerifierReviewTest::verify);
        assertEquals("Review does not belong to user", exception.getMessage());
    }

}

