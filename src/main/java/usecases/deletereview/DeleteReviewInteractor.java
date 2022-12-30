package usecases.deletereview;

import entity.Internship;
import entity.Review;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;
import usecases.comment.exceptions.ReviewNotFoundException;

import java.util.UUID;
import java.util.stream.Collectors;

/** The deletereview use case interactor that calls the deleteReview method from the
 * IDeleteReviewInput input boundary. When initialized, takes in an object that implements
 * IInternshipDAO to acces the internship database through, an object that implements IReviewDAO
 * to access the review database through, and an object that implements IUserDAO to access
 * the user database through.
 */
public class DeleteReviewInteractor implements IDeleteReviewInput{

    private final IInternshipDAO dataAccessInternship;
    private final IReviewDAO dataAccessReview;

    public DeleteReviewInteractor(IReviewDAO dataAccessReview, IInternshipDAO dataAccessInternship, IUserDAO userDAO){
        this.dataAccessReview = dataAccessReview;
        this.dataAccessInternship = dataAccessInternship;
    }

    /** Deletes the review as specified by the inputs in requestModel from the review database.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public DeleteReviewResponseModel deleteReview(DeleteReviewRequestModel requestModel){
        UUID internshipId = requestModel.getInternshipId();
        UUID reviewId = requestModel.getReviewId();
        Internship internship;
        Review review;

        try {
            review = dataAccessReview.getReview(reviewId);
            if (review == null){
                throw new ReviewNotFoundException("Review not found");
            }
        } catch (ReviewNotFoundException e){
            return new DeleteReviewResponseModel(e.getMessage());
        }

        try{
            internship = dataAccessInternship.getInternship(internshipId);
            internship.setReviews(internship.getReviews().stream().filter(
                    r -> !r.getId().equals(reviewId)
            ).collect(Collectors.toList()));
            dataAccessInternship.save(internship);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        dataAccessReview.delete(reviewId);
        return new DeleteReviewResponseModel("Review has successfully been deleted");
    }
}
