package controllers;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import representation.ConnectionResponseRepresentation;
import user.connect.*;
import representation.ConnectionRequestRepresentation;

import java.security.Principal;

@RestController
public class ConnectionRequestController {
    // this could be a http request
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/users/connections/request")
    // broadcasts to all subscribers of /topic/example
    // we will use entities as representations
    // https://huongdanjava.com/send-stomp-message-to-a-specific-user-with-spring-websocket.html
    public void connection(
            Principal user,
            @Header("simpSessionId") String sessionId,
            @Payload ConnectionRequestRepresentation request) throws ResponseStatusException {
        ConnectionDataAccess dataAccess = new ConnectionDataAccess();
        ConnectionInteractor interactor = new ConnectionInteractor(dataAccess);
        ConnectionController controller = new ConnectionController(interactor);
        ConnectionResponseModel response = controller.requestConnection(request.getUserId(), request.getTargetId());
        if (response.getConnectionStatus() == ServerStatus.ERROR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response.getMessage());
        }
        ConnectionResponseRepresentation userResponseRepresentation =
                new ConnectionResponseRepresentation(response.getUser(),
                        "Successfully sent a request to " + response.getTarget().getId());
        // the response should contain the updated info
        simpMessagingTemplate.convertAndSendToUser(
                request.getTargetId(), "/queue/connections/response", userResponseRepresentation
        );
        ConnectionResponseRepresentation targetResponseRepresentation =
                new ConnectionResponseRepresentation(response.getTarget(),
                        "received a connection request from " + response.getUser().getId());
        simpMessagingTemplate.convertAndSendToUser(
                sessionId, "/queue/connections/response", targetResponseRepresentation
        );
    }

    @MessageExceptionHandler
    @SendToUser(destinations = "/queue/errors", broadcast = false)
    public String handleException(ResponseStatusException exception) {
        return exception.getMessage();
    }
}
