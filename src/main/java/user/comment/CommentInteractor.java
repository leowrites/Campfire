package user.comment;

import entity.Comment;
import entity.Review;
import org.apache.catalina.Server;
import user.comment.exceptions.ReviewNotFoundException;
import service.ServerStatus;

import java.util.ArrayList;
import java.util.Date;

public class CommentInteractor extends CommentObservable implements ICommentInputBoundary {
    private final ICommentDataAccess dataAccess;

    public CommentInteractor(ICommentDataAccess dataAccess) {
        this.dataAccess = dataAccess;
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
            // review = dataAccess.getReview(reviewID);
            review = new Review("1", "test100", "Apple", "", 5);
            if (review == null) {
                throw new ReviewNotFoundException("Review does not exist.");
            }
        }
        catch (ReviewNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage());
        }
        
        String commentId = dataAccess.saveComment(comment);

        ArrayList<String> reviewComments = review.getComments();
        reviewComments.add(commentId);
        review.setComments(reviewComments);

        dataAccess.updateReview(review);
        
        // notify observers that a new comment has been made

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.", reviewComments);
    }
}
