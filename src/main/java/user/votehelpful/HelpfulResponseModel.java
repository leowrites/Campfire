package user.votehelpful;

public class HelpfulResponseModel {
    private String successful;
    private String message;

    public HelpfulResponseModel(String successful) {
        this.successful = successful;
    }

    public HelpfulResponseModel(String successful, String message) {
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
