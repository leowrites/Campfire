package user.createcorporate;
import service.ServerStatus;

public class CorporateGenerateResponseModel {
    private final String message;
    private final ServerStatus status;
    private final int corporateId;

    public CorporateGenerateResponseModel(ServerStatus status, String message) {
        this.status = status;
        this.message = message;
        this.corporateId = 0;
    }

    public CorporateGenerateResponseModel(ServerStatus status, String message, int corporateId) {
        this.status = status;
        this.message = message;
        this.corporateId = corporateId;
    }

    public ServerStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCorporateId() {
        return this.corporateId;
    }
}