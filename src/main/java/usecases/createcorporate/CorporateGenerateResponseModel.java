package usecases.createcorporate;
import service.ServerStatus;

import java.util.UUID;

/** A response model for the createcorporate use case that frames the output data into an object.
 * Holds a String message to go with the status in message, the ServerStatus status of the corporate
 * creation done in CorporateGenerateInteractor in status, and an int representation of the
 * corporate's id in corporateId.
 */
public class CorporateGenerateResponseModel {
    private final String message;
    private final ServerStatus status;
    private UUID corporateId;

    /** A constructor for CorporateGeneralResponseModel that takes in only two parameters, status
     * and message.
     * @param status the ServerStatus status
     * @param message the message to go with the status
     */
    public CorporateGenerateResponseModel(ServerStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /** A constructor for CorporateGeneralResponseModel that takes in all three parameters, status,
     * message and corporateId.
     * @param status the ServerStatus status
     * @param message the message to go with the status
     * @param corporateId the id of the corporate
     */
    public CorporateGenerateResponseModel(ServerStatus status, String message, UUID corporateId) {
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

    public UUID getCorporateId() {
        return this.corporateId;
    }
}