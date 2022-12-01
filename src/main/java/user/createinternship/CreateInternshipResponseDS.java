package user.createinternship;

public class CreateInternshipResponseDS {
    private final String success_status;

    public CreateInternshipResponseDS(String success_status) {
        this.success_status = success_status;
    }

    public String getSuccess_status() {
        return success_status;
    }
}
