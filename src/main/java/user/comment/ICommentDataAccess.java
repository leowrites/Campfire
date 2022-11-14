package user.comment;

import entity.Review;

public interface ICommentDataAccess {
    Review getReview(String reviewID);
}
