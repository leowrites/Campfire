package usecases.postreview;
import entity.Internship;
import entity.Review;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;
import usecases.exceptions.InternshipNotFoundException;

/** The postreview use case interactor that calls the addReviewToCorporate from the
 * IPostReview input boundary. When initialized, takes in an object that implements
 * IReviewDAO to access the review database through, and takes in an object that implements
 * IInternshipDAO to access the internship database through.
 */
public class PostReview implements IPostReview{

    private final IReviewDAO reviewDAO;
    private final IInternshipDAO internshipDAO;
    private final PostReviewFactory reviewFactory;
    public PostReview(IReviewDAO reviewDAO, IInternshipDAO internshipDAO, PostReviewFactory reviewFactory) {
        this.reviewDAO = reviewDAO;
        this.internshipDAO = internshipDAO;
        this.reviewFactory = reviewFactory;
    }

    /** Adds a review to the corporate page database.
     * @param request the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public PostReviewResponse addReviewToInternship(PostReviewRequest request) {
        Internship internship;
        try {
            internship = internshipDAO.getInternshipByID(Integer.parseInt(request.getInternshipId()));
        } catch (InternshipNotFoundException e) {
            return new PostReviewResponse(ServerStatus.ERROR, e.getMessage());
        }
        Review review = reviewFactory.createReview(request.getUsername(),
                request.getReviewContent(),
                request.getRating());
        int reviewId = reviewDAO.saveReview(review, Integer.parseInt(request.getInternshipId()));
        internship.getReviews().add(reviewId);
        internshipDAO.updateInternship(Integer.parseInt(request.getInternshipId()), internship);
        return new PostReviewResponse(ServerStatus.SUCCESS,
                String.format("You have successfully posted a review to reviewId %s", reviewId));
    }
}
