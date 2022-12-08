package user.comment;

import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import user.comment.exceptions.ParentNotFoundException;

/** A class in the comment use case that serves to create IParentOperationsStrategy objects as a
 * factory.
 */
public class ParentOperationsStrategyFactory {

    /** Creates an IParentOperationsStrategy object depending on the parent type.
     * @param parentType the type of the parent to the comment (review or comment)
     * @param commentDAO an object to access the comment database through
     * @param reviewDAO an object to access the review database through
     * @return an IParentOperationsStrategy-implementing operation strategy object
     * @throws ParentNotFoundException
     */
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
