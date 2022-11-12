package user.comment;

// add observer stuff
// right now (11/10) is just a standard response model class
public class ReviewResponseModel {
    private final boolean successful;

    public ReviewResponseModel(boolean successful) {
        this.successful = successful;
    }

    public boolean getSuccessful() {
        return this.successful;
    }
}
