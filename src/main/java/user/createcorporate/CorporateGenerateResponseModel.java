package user.createcorporate;
import service.ServerStatus;

public class CorporateGenerateResponseModel {
    private final String message;
    private final ServerStatus status;

    public CorporateGenerateResponseModel(ServerStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}