package user.votehelpful;

import entity.Review;

public class HelpfulDataAccess implements IHelpfulDataAccess{

    @Override
    public Review getReview(String reviewID) {
        // figure out how to access reviews in the database
        Review review = new Review();
        return review;
    }
}
