package usecases.comment;

import entity.IUserPost;
import entity.Review;
import service.dao.IReviewDAO;
import usecases.comment.exceptions.ParentNotFoundException;

/** One of the operations strategy classes in the comment use case, for Reviews. Takes in an
 * object that implements IReviewDAO to access the review database through.
 */
public class ReviewOperations implements IParentOperationsStrategy {

    private final IReviewDAO reviewDAO;

    public ReviewOperations(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    /** Gets the parent object with the given id parentId.
     * @param parentId the parent's id
     * @return an IUserPost-implementing object
     * @throws ParentNotFoundException thrown when the parent with id parentId cannot be found
     */
    @Override
    public IUserPost getParent(int parentId) throws ParentNotFoundException {
        Review review = reviewDAO.getReview(parentId);
        if (review == null) {
            throw new ParentNotFoundException("Review does not exist.");
        }
        return review;
    }

    /** Updates the corresponding database with the parent object and its id.
     * @param parent the parent object
     * @param parentId the parent's id
     */
    @Override
    public void updateParent(IUserPost parent, int parentId) {
        reviewDAO.updateReview((Review) parent, parentId);
    }
}
