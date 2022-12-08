package usecases.votehelpful;

public enum VoteDecision {
    HELPFUL, UNHELPFUL, NONE;

    @Override
    public String toString() {
        switch(this) {
            case HELPFUL:
                return "Helpful";
            case UNHELPFUL:
                return "Unhelpful";
            default:
                return "None";
        }
    }
}
