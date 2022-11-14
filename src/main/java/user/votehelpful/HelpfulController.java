package user.votehelpful;

public class HelpfulController {
    private final IHelpfulInputBoundary input;

    public HelpfulController(IHelpfulInputBoundary input) {
        this.input = input;
    }

    public HelpfulResponseModel create(HelpfulRequestModel requestModel) {
        return input.create(requestModel);
    }
}
