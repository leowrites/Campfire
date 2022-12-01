package user.deletereview;

import entity.Internship;
import entity.Review;
import exceptions.NotEnoughAccessLevelException;
import service.dao.InternshipDAO;
import service.dao.ReviewDAO;
import user.comment.exceptions.ReviewNotFoundException;
import user.deletecomment.AccessLevelVerifier;
import exceptions.NotOwnReviewException;


public class DeleteReviewInteractor implements IDeleteReviewInput{

    private final InternshipDAO dataAccessInternship;
    private final ReviewDAO dataAccessReview;

    public DeleteReviewInteractor(ReviewDAO dataAccessReview, InternshipDAO dataAccessInternship){
        this.dataAccessReview = dataAccessReview;
        this.dataAccessInternship = dataAccessInternship;
    }

    public DeleteReviewResponseModel createResponseModel(DeleteReviewRequestModel requestModel){
        int internshipId = requestModel.getInternshipId();
        int reviewId = requestModel.getReviewId();
        String userId = requestModel.getUserId();
        int accessLevel = requestModel.getAccessLevel();


        Internship internship;
        Internship newInternship;
        Review review;

        // 2. delete this review from the Internship table
        //  get internship from


        //see if Review exists in DB
        try {
            review = dataAccessReview.getReview(reviewId);
            if (review == null){
                throw new ReviewNotFoundException("Review not found");
            }
        } catch (ReviewNotFoundException e){
            return new DeleteReviewResponseModel(e.getMessage(), dataAccessReview.getAllReviews());
        }

        //see if user has accessLevel

        AccessLevelVerifier accessLevelVerifier = new AccessLevelVerifier(accessLevel);

        try {
            accessLevelVerifier.verify();
        } catch (NotEnoughAccessLevelException e){
            return new DeleteReviewResponseModel(e.getMessage(), dataAccessReview.getAllReviews());
        }

        //see if Review belongs to user
        OwnerVerifierReview ownerVerifierReview = new OwnerVerifierReview(review, userId);

        try {
            ownerVerifierReview.verify();
        } catch (NotOwnReviewException e){
            return new DeleteReviewResponseModel(e.getMessage(), dataAccessReview.getAllReviews());
        }

        // 1. Delete this review from the Review table
        // dataAccessReview.delete(reviewId);

        // 2. Delete this review from the internship that holds it
        internship = dataAccessInternship.getInternship(internshipId);
        DeleteReviewHandler deleteReviewHandler = new DeleteReviewHandler(internship, reviewId);
        newInternship = deleteReviewHandler.deleteReview();

        dataAccessInternship.saveInternship(newInternship);

        //return a success message, as well as the new Arraylist of Reviews
        return new DeleteReviewResponseModel("Review has successfully been deleted",
                dataAccessReview.getAllReviews());

    }
}
