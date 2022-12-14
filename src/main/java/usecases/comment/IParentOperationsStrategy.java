package usecases.comment;

import entity.IUserPost;
import usecases.comment.exceptions.ParentNotFoundException;

import java.util.UUID;

/** An interface that is intended to be implemented by the operations classes created by the
 * ParentOperationsStrategyFactory factory. Holds two methods, getParent and updateParent, that
 * must be implemented in all classes that implement this interface.
 */
public interface IParentOperationsStrategy {

    /** Gets the parent given the parentId.
     * @param parentId the parent's id
     * @return an IUserPost-implementing object
     * @throws ParentNotFoundException thrown when the parent with id parentId cannot be found
     */
    IUserPost getParent(UUID parentId) throws ParentNotFoundException;

    /** Updates the parent.
     * @param parent the parent object
     * @param parentId the parent's id
     */
    void updateParent(IUserPost parent, int parentId);

    void updateParent(IUserPost parent);
}
