package usecases.deletereview;

import entity.Internship;
import entity.Review;
import entity.User;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;
import usecases.comment.exceptions.ReviewNotFoundException;
import usecases.requestconnect.exceptions.UserNotFoundException;

/** The deletereview use case interactor that calls the deleteReview method from the
 * IDeleteReviewInput input boundary. When initialized, takes in an object that implements
 * IInternshipDAO to acces the internship database through, an object that implements IReviewDAO
 * to access the review database through, and an object that implements IUserDAO to access
 * the user database through.
 */
public class DeleteReviewInteractor implements IDeleteReviewInput{

    private final IInternshipDAO dataAccessInternship;
    private final IReviewDAO dataAccessReview;
    private final IUserDAO userDAO;

    public DeleteReviewInteractor(IReviewDAO dataAccessReview, IInternshipDAO dataAccessInternship, IUserDAO userDAO){
        this.dataAccessReview = dataAccessReview;
        this.dataAccessInternship = dataAccessInternship;
        this.userDAO = userDAO;
    }

    /** Deletes the review as specified by the inputs in requestModel from the review database.
     * @param requestModel the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public DeleteReviewResponseModel deleteReview(DeleteReviewRequestModel requestModel){
        int internshipId = requestModel.getInternshipId();
        int reviewId = requestModel.getReviewId();
        String userId = requestModel.getUserId();
        Internship internship;
        Review review;
        User user;

        try {
            review = dataAccessReview.getReview(reviewId);
            user = userDAO.getUser(userId);
            if (review == null){
                throw new ReviewNotFoundException("Review not found");
            }
        } catch (ReviewNotFoundException | UserNotFoundException e){
            return new DeleteReviewResponseModel(e.getMessage());
        }

        if (user.getAccessLevel() == 0 && !userId.equals(review.getUserId())){
            return new DeleteReviewResponseModel("Not authorized!");
        }

        dataAccessReview.deleteReview(reviewId);

        try{
            internship = dataAccessInternship.getInternshipByID(internshipId);
            dataAccessInternship.updateInternship(internshipId, internship);
        }
        catch(Exception e){System.out.println(e.getMessage());}
        // need to recursively delete all comments ...
        // will do in the future
        //return a success message, as well as the new Arraylist of Reviews
        return new DeleteReviewResponseModel("Review has successfully been deleted");
    }
}
