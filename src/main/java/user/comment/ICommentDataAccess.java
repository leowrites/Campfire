package user.comment;

import entity.Review;
import entity.Comment;

public interface ICommentDataAccess {
    Review getReview(String reviewID);

    void updateReview(Review review);

    String saveComment(Comment comment);

    // Comment getComment(String commentID);
}
