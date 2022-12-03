package user.deletereview;

public class DeleteReviewResponseModel {
    private final String message;

    public DeleteReviewResponseModel(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
