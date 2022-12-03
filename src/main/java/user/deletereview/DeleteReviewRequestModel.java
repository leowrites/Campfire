package user.deletereview;

public class DeleteReviewRequestModel {

    private final int internshipId;

    private final int reviewId;

    private final String userId;

    public DeleteReviewRequestModel(int internshipId,
                                    int reviewId,
                                    String userId){
        this.internshipId = internshipId;
        this.reviewId = reviewId;
        this.userId = userId;
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
}
