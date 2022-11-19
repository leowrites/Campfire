package user.requestconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@ComponentScan("main")
public class RequestConnectionController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IRequestConnectionInput requestConnectionInteractor;

    @Autowired
    public RequestConnectionController(IRequestConnectionInput requestConnectionInteractor,
                                       SimpMessagingTemplate simpMessagingTemplate) {
        this.requestConnectionInteractor = requestConnectionInteractor;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @MessageMapping("/users/connections/request")
    public void requestConnection(
            Principal user,
            @Header("simpSessionId") String sessionId,
            @Payload RequestConnectionRequestModel requestModel){
        RequestConnectionResponseModel requestConnectionResponseModel = requestConnectionInteractor.requestConnection(requestModel);
        simpMessagingTemplate.convertAndSend("/topic/users/connections/request", requestConnectionResponseModel);
        // send to target
//        simpMessagingTemplate.convertAndSendToUser(
//                requestModel.getTargetId(), "/queue/connections", requestConnectionResponseModel
//        );
//
        // send to current user
//        simpMessagingTemplate.convertAndSendToUser(
//                requestModel.getUserId(), "/queue/connections", requestConnectionResponseModel
//        );
    }
}
