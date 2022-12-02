package user.deletereview;

import entity.Internship;
import entity.Review;
import service.dao.IInternshipDAO;
import service.dao.IReviewDAO;
import user.comment.exceptions.ReviewNotFoundException;

public class DeleteReviewInteractor implements IDeleteReviewInput{

    private final IInternshipDAO dataAccessInternship;
    private final IReviewDAO dataAccessReview;

    public DeleteReviewInteractor(IReviewDAO dataAccessReview, IInternshipDAO dataAccessInternship){
        this.dataAccessReview = dataAccessReview;
        this.dataAccessInternship = dataAccessInternship;
    }

    public DeleteReviewResponseModel deleteReview(DeleteReviewRequestModel requestModel){
        int internshipId = requestModel.getInternshipId();
        int reviewId = requestModel.getReviewId();
        int accessLevel = requestModel.getAccessLevel();
        String userId = requestModel.getUserId();
        Internship internship;
        Review review;

        try {
            review = dataAccessReview.getReview(reviewId);
            if (review == null){
                throw new ReviewNotFoundException("Review not found");
            }
        } catch (ReviewNotFoundException e){
            return new DeleteReviewResponseModel(e.getMessage(), null);
        }

        //see if user has accessLevel

//        AccessLevelVerifier accessLevelVerifier = new AccessLevelVerifier(accessLevel);
//
//        try {
//            accessLevelVerifier.verify();
//        } catch (NotEnoughAccessLevelException e){
//            return new DeleteReviewResponseModel(e.getMessage(), null);
//        }
//
//        //see if Review belongs to user
//        OwnerVerifierReview ownerVerifierReview = new OwnerVerifierReview(review, userId);
//
//        try {
//            ownerVerifierReview.verify();
//        } catch (NotOwnReviewException e){
//            return new DeleteReviewResponseModel(e.getMessage(), null);
//        }

        if (accessLevel == 0 && !userId.equals(review.getUserId())) {
            return new DeleteReviewResponseModel("Not authorized!", null);
        }

        dataAccessReview.deleteReview(reviewId);

        internship = dataAccessInternship.getInternship(internshipId);
//        DeleteReviewHandler deleteReviewHandler = new DeleteReviewHandler(internship, reviewId);
//        newInternship = deleteReviewHandler.deleteReview();
//        List<Integer> filteredReviews = internship.getReviews()
//                .stream()
//                .filter(id -> id != reviewId)
//                .collect(Collectors.toList());
//        internship.setReviews((ArrayList<Integer>) filteredReviews);
        dataAccessInternship.update(internship, internshipId);

        // need to recursively delete all comments ...


        //return a success message, as well as the new Arraylist of Reviews
        return new DeleteReviewResponseModel("Review has successfully been deleted", null);
    }
}
