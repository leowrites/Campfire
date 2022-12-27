package usecases.comment;

import entity.Comment;

/** A class in the comment use case that serves to create Comment objects as a factory.
 */
public class CommentFactory {

    /** Creates a Comment.
     * @param content the content of the comment
     * @return a Comment object that is newly created
     */
    public Comment createComment(String content) {
        return new Comment(content);
    }

}
