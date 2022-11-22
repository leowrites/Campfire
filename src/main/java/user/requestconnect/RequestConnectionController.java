package user.requestconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import service.ServerStatus;

import java.security.Principal;

@RestController
@ComponentScan("main")
public class RequestConnectionController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IRequestConnectionInput requestConnectionInteractor;

    @Autowired
    SimpUserRegistry userRegistry;

    public void findThem() {
        System.out.println(userRegistry.getUsers());
    }
    @Autowired
    public RequestConnectionController(IRequestConnectionInput requestConnectionInteractor,
                                       SimpMessagingTemplate simpMessagingTemplate) {
        this.requestConnectionInteractor = requestConnectionInteractor;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
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
                    requestConnectionResponseModel);
        }
    }
}
