package usecases.acceptconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import service.ServerStatus;

/** The acceptconnect use case controller that connects to Spring. Takes in an
 * AcceptConnectionRequestModel from the user input in front-end, creates an
 * AcceptConnectionResponseModel by sending the request model to the interactor, and maps a message
 * back to the front-end.
 */
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

    /** Creates a message to send back to the front-end using the inputs in requestModel.
     * @param requestModel the AcceptConnectionRequestModel taken in from the front-end
     */
    @MessageMapping("/users/connections/accept")
    public void acceptConnection(
            @Payload AcceptConnectionRequestModel requestModel
    ){
        System.out.println("received message");
        AcceptConnectionResponseModel responseModel = interactor.acceptConnection(requestModel);
        if (responseModel.getServerStatus() == ServerStatus.SUCCESS) {
            simpMessagingTemplate.convertAndSendToUser(requestModel.getUserId(), "/queue/connections/accept",
                    responseModel.getUserResponseModel());
            simpMessagingTemplate.convertAndSendToUser(requestModel.getTargetId(), "/queue/connections/accept",
                    responseModel.getTargetResponseModel());
        } else {
            simpMessagingTemplate.convertAndSendToUser(requestModel.getUserId(), "/queue/connections/accept",
                    responseModel);
        }
    }
}
