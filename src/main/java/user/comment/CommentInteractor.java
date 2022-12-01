package user.comment;

import entity.Comment;
import entity.Review;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import user.comment.exceptions.ReviewNotFoundException;
import service.ServerStatus;

import java.util.ArrayList;
import java.util.Date;

public class CommentInteractor extends CommentObservable implements ICommentInputBoundary {
    private final IReviewDAO reviewDAO;
    private final ICommentDAO commentDAO;

    public CommentInteractor(IReviewDAO reviewDAO, ICommentDAO commentDAO) {
        this.reviewDAO = reviewDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    public CommentResponseModel create(CommentRequestModel requestModel) {
        String userId = requestModel.getUserId();
        int reviewId = requestModel.getReviewId();
        String content = requestModel.getContent();
        Review review;
        Comment comment = new Comment(userId, content);

        try {
            review = reviewDAO.getReview(reviewId);
            if (review == null) {
                throw new ReviewNotFoundException("Review does not exist.");
            }
        }
        catch (ReviewNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage());
        }
        
        int commentId = commentDAO.saveComment(comment);

        ArrayList<Integer> reviewComments = review.getComments();
        reviewComments.add(commentId);
        review.setComments(reviewComments);

        reviewDAO.updateReview(review, reviewId);
        
        // notify observers that a new comment has been made

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.");
    }
}

