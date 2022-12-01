package user.deletereview;

public class DeleteReviewRequestModel {

    private final int internshipId;

    private final int reviewId;

    private final String userId;

    private final int accessLevel;

    public DeleteReviewRequestModel(int internshipId,
                                    int reviewId,
                                    String userId,
                                    int accessLevel){
        this.internshipId = internshipId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.accessLevel = accessLevel;
    }

    public int getInternshipId(){
        return this.internshipId;
    }

    public int getReviewId(){
        return this.reviewId;
    }

    public String getUserId(){
        return this.userId;
    }

    public int getAccessLevel(){
        return this.accessLevel;
    }
}
