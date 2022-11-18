package main;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;
import user.requestconnect.RequestConnectionDataAccess;
import user.requestconnect.exceptions.UserNotFoundException;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;

@RestController
public class TestController {
    @Autowired
    private RequestConnectionDataAccess requestConnectionDataAccess;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") String username,
            HttpSession session
    ) {
        // saving a user works
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
            user = requestConnectionDataAccess.getUser(username);
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
        requestConnectionDataAccess.saveUser(
                new User("01", user1Requests, user1Connections, user1PendingConnections,
                        username, "leo@gmail.com", "pass", "Leo")
        );
    }
}
