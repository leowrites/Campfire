package user.comment;

import entity.IUserPost;
import user.comment.exceptions.ParentNotFoundException;

public interface IParentOperationsStrategy {

    IUserPost getParent(int parentId) throws ParentNotFoundException;

    void updateParent(IUserPost parent, int parentId);
}
