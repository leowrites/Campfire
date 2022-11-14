package user.votehelpful;

import entity.Review;

public interface IHelpfulDataAccess {
    Review getReview(String reviewID);
}
