package delete_comment;

import entity.Comment;

public interface IDeleteCommentDataAccess {

    Comment getComment(String id);
}
