package user.postreview;

import entity.Review;
import org.springframework.security.core.parameters.P;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;

public class PostReview implements IPostReview{

    private final IReviewDAO reviewDAO;
    private final IInternshipDAO internshipDAO;
    private final PostReviewFactory reviewFactory;
    public PostReview(IReviewDAO reviewDAO, IInternshipDAO internshipDAO) {
        this.reviewDAO = reviewDAO;
        this.internshipDAO = internshipDAO;
        this.reviewFactory = new PostReviewFactory();
    }

    /**
     *
     * @param request the request model
     * @return a response model to be sent back to the client
     */
    @Override
    public PostReviewResponse addReviewToCorporate(PostReviewRequest request) {
//      Internship internship = internshipDAO.getInternship(request.getInternshipId());
        Review review = reviewFactory.createReview(request.getUsername(),
                request.getReviewContent(),
                request.getRating());
        int reviewId = reviewDAO.saveReview(review);
//        ArrayList<String> reviews = internship.getReviews();
//        reviews.add(reviewId);
//        internship.setReviews(reviews);
//        internshipDAO.saveInternship(internship);
        return new PostReviewResponse(ServerStatus.SUCCESS,
                String.format("You have successfully posted a review to reviewId %s", reviewId));
    }
}
