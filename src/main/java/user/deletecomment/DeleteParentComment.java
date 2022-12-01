package user.deletecomment;

import entity.Comment;

import java.util.ArrayList;

public class DeleteParentComment {
    private final Comment parentComment;
    private final int commentId;

    public DeleteParentComment(Comment parentComment, int commentId) {
        this.commentId = commentId;
        this.parentComment = parentComment;
    }

    public Comment deleteComment(){
        ArrayList<Integer> commentList = parentComment.getComments();
        int size = commentList.size();
        for (int i = 0; i < size; i++) {
            if (commentId == commentList.get(i)) {
                commentList.remove(i);
                break;
            }
        }
        parentComment.setComments(commentList);
        return parentComment;
        }

    }





