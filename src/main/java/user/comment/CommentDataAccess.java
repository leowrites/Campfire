package user.comment;

import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDataAccess implements ICommentDataAccess {

    @Override
    public Review getReview(String reviewID) {
        // figure out how to access the reviews in the database
        Review review = new Review();
        return review;
    }

}
