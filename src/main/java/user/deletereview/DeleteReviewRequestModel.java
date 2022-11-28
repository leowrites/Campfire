package user.deletereview;

public class DeleteReviewRequestModel {

    private final String reviewId;

    private final String userId;

    private final int accessLevel;

    public DeleteReviewRequestModel(String reviewId,
                                    String userId,
                                    int accessLevel){
        this.reviewId = reviewId;
        this.userId = userId;
        this.accessLevel = accessLevel;
    }

    public String getReviewId(){
        return this.reviewId;
    }

    public String getUserId(){
        return this.userId;
    }

    public int getAccessLevel(){
        return this.accessLevel;
    }
}
