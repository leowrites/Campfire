package user.requestconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.RestController;
import service.ServerStatus;

import java.security.Principal;

/**
 * The controller connects the use case to database using Spring boot to configure.
 */

@RestController
@ComponentScan("main")
public class RequestConnectionController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IRequestConnectionInput requestConnectionInteractor;

    @Autowired
    SimpUserRegistry userRegistry;

    /**
     * Get target users that the principal wishes to connect with from userRegistry
     */
    public void findThem() {
        System.out.println(userRegistry.getUsers());
    }

    /**
     *
     * @param requestConnectionInteractor is the use case interactor
     * @param simpMessagingTemplate provides methods for sending messages to users
     */
    @Autowired
    public RequestConnectionController(IRequestConnectionInput requestConnectionInteractor,
                                       SimpMessagingTemplate simpMessagingTemplate) {
        this.requestConnectionInteractor = requestConnectionInteractor;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * Returns a reponseModel depending on the ServerStatus
     * @param user is the principal who requested to connect
     * @param requestModel has the principal userID and the target userID
     */
    @MessageMapping("/users/connections/request")
    public void requestConnection(
            Principal user,
            @Payload RequestConnectionRequestModel requestModel){
        RequestConnectionResponseModel requestConnectionResponseModel =
                requestConnectionInteractor.requestConnection(requestModel);
        findThem();
        if (requestConnectionResponseModel.getServerStatus() == ServerStatus.SUCCESS) {
            simpMessagingTemplate.convertAndSendToUser(user.getName(), "/queue/connections/request",
                    requestConnectionResponseModel.getUserResponseModel());
            simpMessagingTemplate.convertAndSendToUser(requestModel.getTargetId(), "/queue/connections/request",
                    requestConnectionResponseModel.getTargetResponseModel());
        } else {
            simpMessagingTemplate.convertAndSendToUser(user.getName(), "/queue/connections/request",
                    requestConnectionResponseModel.getUserResponseModel());
        }
    }
}
