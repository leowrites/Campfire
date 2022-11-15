package user.comment;

// add observer stuff
// right now (11/10) is just a standard response model class
// ** do I even need this class????
public class CommentResponseModel {
    private String successful;
    private String message;

    public CommentResponseModel(String successful) {
        this.successful = successful;
        this.message = "";
    }

    public CommentResponseModel(String successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public String getSuccessful() {
        return this.successful;
    }

    public String getMessage() {
        return this.message;
    }
}
