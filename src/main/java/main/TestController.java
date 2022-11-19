package main;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import service.IUserDataAccess;
import user.requestconnect.RequestConnectionRequestModel;
import user.requestconnect.RequestConnectionResponseModel;
import user.requestconnect.ServerStatus;
import user.requestconnect.exceptions.UserNotFoundException;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class TestController {
    @Autowired
    private IUserDataAccess userDataAccess;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") String username,
            HttpSession session
    ) {
//         saving a user works
//        ArrayList<String> user1Requests = new ArrayList<String>();
//        ArrayList<String> user1PendingConnections = new ArrayList<String>();
//        ArrayList<String> user1Connections = new ArrayList<String>();
//        requestConnectionDataAccess.saveUser(
//                new User("01", user1Requests, user1Connections, user1PendingConnections,
//                        username, "leo@gmail.com", "pass", "Leo")
//        );
        System.out.println(session.getId());
        User user;
        try {
            user = userDataAccess.getUser(username);
        } catch (UserNotFoundException e) {
            return null;
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users/{id}")
    public void postUser(
            @PathVariable("id") String username
    ) {
        ArrayList<String> user1Requests = new ArrayList<String>();
        ArrayList<String> user1PendingConnections = new ArrayList<String>();
        ArrayList<String> user1Connections = new ArrayList<String>();
        userDataAccess.saveUser(
                new User("01", user1Requests, user1Connections, user1PendingConnections,
                        username, "leo@gmail.com", "pass", "Leo")
        );
    }

    @MessageMapping("/users/test")
    @SendTo("/topic/users/test")
    public RequestConnectionResponseModel testSocket(
            Principal user,
            @Header("simpSessionId") String sessionId,
            @Payload RequestConnectionRequestModel requestModel
    ) {
        System.out.println("received message");
        System.out.println(sessionId);
        return new RequestConnectionResponseModel(
                ServerStatus.SUCCESS,
                "We received the message",
                "leoliu"
        );
    }

    @GetMapping("/users")
    public ResponseEntity<ArrayList<User>> getUser() {
        return new ResponseEntity<>(userDataAccess.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/users/reset")
    public void resetUser() {
        userDataAccess.reset();
    }
}
