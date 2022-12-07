package user.comment;

import entity.IUserPost;
import entity.Review;
import service.dao.IReviewDAO;
import user.comment.exceptions.ParentNotFoundException;

public class ReviewOperations implements IParentOperationsStrategy {

    private final IReviewDAO reviewDAO;

    public ReviewOperations(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    public IUserPost getParent(int parentId) throws ParentNotFoundException {
        Review review = reviewDAO.getReview(parentId);
        if (review == null) {
            throw new ParentNotFoundException("Review does not exist.");
        }
        return review;
    }

    @Override
    public void updateParent(IUserPost parent, int parentId) {
        reviewDAO.updateReview((Review) parent, parentId);
    }
}
