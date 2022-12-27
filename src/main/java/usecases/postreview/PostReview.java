package usecases.postreview;
import entity.Internship;
import entity.Review;
import entity.User;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;
import service.dao.IUserDAO;
import usecases.exceptions.InternshipNotFoundException;
import usecases.requestconnect.exceptions.UserNotFoundException;

/** The postreview use case interactor that calls the addReviewToCorporate from the
 * IPostReview input boundary. When initialized, takes in an object that implements
 * IReviewDAO to access the review database through, and takes in an object that implements
 * IInternshipDAO to access the internship database through.
 */
public class PostReview implements IPostReview{

    private final IReviewDAO reviewDAO;
    private final IInternshipDAO internshipDAO;
    private final IUserDAO userDAO;
    private final PostReviewFactory reviewFactory;
    public PostReview(IReviewDAO reviewDAO, IInternshipDAO internshipDAO, IUserDAO userDAO, PostReviewFactory reviewFactory) {
        this.reviewDAO = reviewDAO;
        this.internshipDAO = internshipDAO;
        this.userDAO = userDAO;
        this.reviewFactory = reviewFactory;
    }

    /** Adds a review to the corporate page database.
     * @param request the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public PostReviewResponse addReviewToInternship(PostReviewRequest request) {
        Internship internship;
        User user;
        try {
            internship = internshipDAO.getInternship(request.getInternshipId());
        } catch (InternshipNotFoundException e) {
            return new PostReviewResponse(ServerStatus.ERROR, e.getMessage());
        }
        try {
            user = userDAO.getUser(request.getUsername());
        } catch (UserNotFoundException e) {
            return new PostReviewResponse(ServerStatus.ERROR, e.getMessage());
        }
        Review review = reviewFactory.createReview(
                request.getReviewContent(),
                request.getRating());
        review.setUser(user);
        review.setInternshipId(internship.getId());
        Review savedReview = reviewDAO.save(review);

        internship.getReviews().add(savedReview);
        internshipDAO.save(internship);

        return new PostReviewResponse(ServerStatus.SUCCESS,
                String.format("You have successfully posted a review to reviewId %s", savedReview.getId()));
    }
}
