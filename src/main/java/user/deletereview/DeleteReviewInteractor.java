package user.deletereview;

import entity.Internship;
import entity.Review;
import entity.User;
import service.dao.IInternshipDAO;
import service.dao.IReviewDAO;
import service.dao.IUserDAO;
import user.comment.exceptions.ReviewNotFoundException;
import user.requestconnect.exceptions.UserNotFoundException;

public class DeleteReviewInteractor implements IDeleteReviewInput{

    private final IInternshipDAO dataAccessInternship;
    private final IReviewDAO dataAccessReview;
    private final IUserDAO userDAO;

    public DeleteReviewInteractor(IReviewDAO dataAccessReview, IInternshipDAO dataAccessInternship, IUserDAO userDAO){
        this.dataAccessReview = dataAccessReview;
        this.dataAccessInternship = dataAccessInternship;
        this.userDAO = userDAO;
    }

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
        return new DeleteReviewResponseModel("Review has successfully been deleted");
    }
}
