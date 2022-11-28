package user.postreview;

import entity.Internship;
import entity.Review;
import service.IInternshipDAO;
import service.IReviewDAO;
import service.ServerStatus;

import java.util.ArrayList;

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
    public PostReviewResponse addReviewToCorporate(PostReviewRequest request) {
        Internship internship = internshipDAO.getInternship(request.getInternshipId());
        Review review = new Review(
                null,
                request.getUsername(),
                request.getReviewContent(),
                request.getInternshipId()
        );
        long reviewId = reviewDAO.saveReview(review);
        ArrayList<String> reviews = internship.getReviews();
        reviews.add(Long.toString(reviewId));
        internship.setReviews(reviews);
        internshipDAO.saveInternship(internship);
        return new PostReviewResponse(ServerStatus.SUCCESS, "You have successfully posted a review");
    }
}