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
        String id = requestModel.getid();
        String userID = requestModel.getUserID();
        String reviewID = requestModel.getReviewID();
        String content = requestModel.getContent();
        Date datePosted = new Date();
        Review review;
        Comment comment = new Comment(id, userID, content, datePosted);

        try {
            review = dataAccess.getReview(reviewID);
            if (review == null) {
                throw new ReviewNotFoundException("Review does not exist.");
            }
        }
        catch (ReviewNotFoundException e) {
            return new CommentResponseModel(ServerStatus.ERROR, e.getMessage());
        }
        
        dataAccess.insertComment(comment);

        ArrayList<String> reviewComments = review.getComments();
        reviewComments.add(comment.getID());
        review.setComments(reviewComments);

        dataAccess.updateReview(review);
        
        // notify observers that a new comment has been made

        return new CommentResponseModel(ServerStatus.SUCCESS, "Comment posted successfully.", reviewComments);
    }
}
