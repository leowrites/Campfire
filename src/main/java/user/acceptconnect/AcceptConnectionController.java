package user.acceptconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import service.ServerStatus;

@RestController
@ComponentScan("main")
public class AcceptConnectionController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IAcceptConnectionInput interactor;

    @Autowired
    public AcceptConnectionController(IAcceptConnectionInput interactor,
                                      SimpMessagingTemplate simpMessagingTemplate){
        this.interactor = interactor;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/users/connections/accept")
    public void acceptConnection(String userName, String targetName){
        AcceptConnectionRequestModel requestModel = new AcceptConnectionRequestModel(userName, targetName);
    }
}
