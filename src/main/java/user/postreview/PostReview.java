package user.postreview;

import entity.Review;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;

/** The postreview use case interactor that calls the addReviewToCorporate from the
 * IPostReview input boundary. When initialized, takes in an object that implements
 * IReviewDAO to access the review database through, and takes in an object that implements
 * IInternshipDAO to access the internship database through.
 */
public class PostReview implements IPostReview{

    private final IReviewDAO reviewDAO;
    private final IInternshipDAO internshipDAO;
    public PostReview(IReviewDAO reviewDAO, IInternshipDAO internshipDAO) {
        this.reviewDAO = reviewDAO;
        this.internshipDAO = internshipDAO;
    }

    /** Adds a review to the corporate page database.
     * @param request the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public PostReviewResponse addReviewToCorporate(PostReviewRequest request) {
//      Internship internship = internshipDAO.getInternship(request.getInternshipId());
        Review review = new Review(
                request.getUsername(),
                request.getReviewContent(),
                request.getRating()
        );
        int reviewId = reviewDAO.saveReview(review);
//        ArrayList<String> reviews = internship.getReviews();
//        reviews.add(reviewId);
//        internship.setReviews(reviews);
//        internshipDAO.saveInternship(internship);
        return new PostReviewResponse(ServerStatus.SUCCESS,
                String.format("You have successfully posted a review to reviewId %s", reviewId));
    }
}
