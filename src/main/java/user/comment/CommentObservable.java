package user.comment;
import java.util.ArrayList;

public class CommentObservable {
    private final ArrayList<CommentObserver> observers;

    public CommentObservable() {
        this.observers = new ArrayList<CommentObserver>();
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
