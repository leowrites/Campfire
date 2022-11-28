package user.deletereview;

import entity.Review;
import exceptions.NotEnoughAccessLevelException;
import user.comment.exceptions.ReviewNotFoundException;
import user.deletecomment.AccessLevelVerifier;
import exceptions.NotOwnReviewException;

import java.util.ArrayList;

public class DeleteReviewInteractor implements IDeleteReviewInput{

    private final IDeleteReviewDataAccess dataAccess;

    public DeleteReviewInteractor(IDeleteReviewDataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    public DeleteReviewResponseModel createResponseModel(DeleteReviewRequestModel requestModel){
        String reviewId = requestModel.getReviewId();
        String userId = requestModel.getUserId();
        int accessLevel = requestModel.getAccessLevel();

        ArrayList<Review> reviews = dataAccess.getReviews();
        ArrayList<Review> reviewsNew;
        Review review;

        //see if Review exists in DB
        try {
            review = dataAccess.getReview(reviewId);
            if (review == null){
                throw new ReviewNotFoundException("Review not found");
            }
        } catch (ReviewNotFoundException e){
            return new DeleteReviewResponseModel(e.getMessage(), reviews);
        }

        //see if user has accessLevel

        AccessLevelVerifier accessLevelVerifier = new AccessLevelVerifier(accessLevel);

        try {
            accessLevelVerifier.verify();
        } catch (NotEnoughAccessLevelException e){
            return new DeleteReviewResponseModel(e.getMessage(), reviews);
        }

        //see if Review belongs to user
        OwnerVerifierReview ownerVerifierReview = new OwnerVerifierReview(review, userId);

        try {
            ownerVerifierReview.verify();
        } catch (NotOwnReviewException e){
            return new DeleteReviewResponseModel(e.getMessage(), reviews);
        }

        //use the reviewId, delete the review from the database

        DeleteReviewHandler deleteReviewHandler = new DeleteReviewHandler(reviewId, reviews);

        reviewsNew = deleteReviewHandler.deleteReview();

        //update the database with the new Reviews
        dataAccess.updateReviews(reviewsNew);

        //return a success message, as well as the new Arraylist of Reviews
        return new DeleteReviewResponseModel("Review has successfully been deleted", reviewsNew);

    }
}
