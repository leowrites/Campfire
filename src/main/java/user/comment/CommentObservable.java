package user.comment;
import java.util.ArrayList;

// turn into abstract base class
public abstract class CommentObservable {
    private final ArrayList<CommentObserver> observers;

    public CommentObservable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(CommentObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(CommentObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (CommentObserver observer : this.observers) {
            observer.update();
        }
    }
}
