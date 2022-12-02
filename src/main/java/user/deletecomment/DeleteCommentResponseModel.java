package user.deletecomment;

public class DeleteCommentResponseModel {

    private final String message;

    public DeleteCommentResponseModel(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
