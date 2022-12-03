package user.postreview;
import entity.Internship;
import entity.Review;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;
import user.exceptions.InternshipNotFoundException;

public class PostReview implements IPostReview{

    private final IReviewDAO reviewDAO;
    private final IInternshipDAO internshipDAO;
    public PostReview(IReviewDAO reviewDAO, IInternshipDAO internshipDAO) {
        this.reviewDAO = reviewDAO;
        this.internshipDAO = internshipDAO;
    }

    /**
     *
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
        Review review = new Review(
                request.getUsername(),
                request.getReviewContent(),
                request.getRating()
        );
        int reviewId = reviewDAO.saveReview(review, Integer.parseInt(request.getInternshipId()));
        internship.getReviews().add(reviewId);
        internshipDAO.updateInternship(Integer.parseInt(request.getInternshipId()), internship);
        return new PostReviewResponse(ServerStatus.SUCCESS,
                String.format("You have successfully posted a review to reviewId %s", reviewId));
    }
}
