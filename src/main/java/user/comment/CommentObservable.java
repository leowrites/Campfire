package user.comment;
import java.util.ArrayList;

/** An abstract class that can add, remove, and notify CommentObservers.
 */
public abstract class CommentObservable {
    private final ArrayList<CommentObserver> observers;

    public CommentObservable() {
        this.observers = new ArrayList<>();
    }

    /** Adds a CommentObserver to the list.
     * @param observer a CommentObserver
     */
    public void addObserver(CommentObserver observer) {
        this.observers.add(observer);
    }

    /** Removes a CommentObserver from the list.
     * @param observer a CommentObserver
     */
    public void removeObserver(CommentObserver observer) {
        this.observers.remove(observer);
    }

    /** Updates all observers.
     */
    public void notifyObservers() {
        for (CommentObserver observer : this.observers) {
            observer.update();
        }
    }
}
