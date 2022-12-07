package user.comment;

import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import user.comment.exceptions.ParentNotFoundException;

public class ParentOperationsStrategyFactory {

    public static IParentOperationsStrategy getStrategy(String parentType, ICommentDAO commentDAO, IReviewDAO reviewDAO) throws ParentNotFoundException {
        switch (parentType) {
            case "Review":
                return new ReviewOperations(reviewDAO);
            case "Comment":
                return new CommentOperations(commentDAO);
            default:
                throw new ParentNotFoundException("Invalid parent type.");
        }
    }

}
