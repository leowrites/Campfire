package user.requestconnect;

public enum ServerStatus {
    ERROR, SUCCESS;
    @Override
    public String toString() {
        switch(this) {
            case ERROR:
                return "error";
            case SUCCESS:
                return "success";
            default:
                return "unknown";
        }
    }
}
