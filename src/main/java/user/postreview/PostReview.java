package user.postreview;

import entity.Review;
import service.dao.IReviewDAO;
import service.dao.IInternshipDAO;
import service.ServerStatus;

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
