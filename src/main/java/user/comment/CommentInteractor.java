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
        String reviewId = requestModel.getReviewId();
        String content = requestModel.getContent();
        Date datePosted = new Date();
        Review review;
        Comment comment = new Comment(userId, content, datePosted);

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

        ArrayList<String> reviewComments = review.getComments();
        reviewComments.add(Integer.toString(commentId));
        review.setComments(reviewComments);

        review.setId(reviewId);
        reviewDAO.updateReview(review, Integer.parseInt(reviewId));
        
        // notify observers that a new comment has been made

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.");
    }
}
