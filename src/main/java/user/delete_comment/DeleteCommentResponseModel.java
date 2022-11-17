package user.delete_comment;

public class DeleteCommentResponseModel {

    private final String message;

    public DeleteCommentResponseModel(String message){
        this.message = message;
    }

    public String getmessage(){
        return this.message;
    }
}
