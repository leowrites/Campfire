package user.votehelpful;

import entity.Review;
import service.ServerStatus;
import service.dao.IReviewDAO;
import user.votehelpful.exceptions.ReviewNotFoundException;

/** The votehelpful use case interactor that calls the create method from the
 * IHelpfulInputBoundary input boundary that separates it from the other layers of Clean
 * Architecture. When initialized this interactor takes in an object that implements
 * IReviewDAO to access the database through.
 */
public class HelpfulInteractor implements IHelpfulInputBoundary {
    private final IReviewDAO reviewDAO;

    public HelpfulInteractor(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    /** Creates a HelpfulResponseModel by first checking if there is a Review with the given
     * id as in the request model, returning a failure in the ServerStatus and an appropriate
     * message if the review does not exist. If the review does exist, it either adds to the
     * number of likes or dislikes based on the isHelpful boolean in the request model, updates
     * the review in the database through reviewDAO, and returns a response model with a
     * success in the ServerStatus and an appropriate message.
     * @param requestModel a request model that contains a boolean representation of if
     *                     the review is helpful and an int representing the Review's id
     * @return a response model holding a ServerStatus and a String message
     */
    @Override
    public HelpfulResponseModel create(HelpfulRequestModel requestModel) {
        boolean isHelpful = requestModel.getIsHelpful();
        int reviewId = requestModel.getReviewId();
        Review review;

        try {
            review = reviewDAO.getReview(reviewId);
            if (review == null) {
                throw new ReviewNotFoundException("Review does not exist.");
            }
        }
        catch (ReviewNotFoundException e) {
            return new HelpfulResponseModel(ServerStatus.ERROR, e.getMessage());
        }

        if (isHelpful) {
            int numLikes = review.getNumLikes();
            numLikes = numLikes + 1;
            review.setNumLikes(numLikes);
        }
        else {
            int numDislikes = review.getNumDislikes();
            numDislikes = numDislikes + 1;
            review.setNumDislikes(numDislikes);
        }

        reviewDAO.updateReview(review, reviewId);

        return new HelpfulResponseModel(ServerStatus.SUCCESS, "Vote received.");
    }
}
