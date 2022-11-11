package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import representation.ExampleReceive;
import representation.ExampleSend;
import main.Controller;


@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Controller controller = new Controller();
        controller.myAction();

//        Object[] parameters = new Object[] {new Integer(2)};
//        Object o = jt.queryForObject("select name from elephant where id = ?",
//                parameters, String.class);
//        System.out.println((String)o);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @MessageMapping("/example")
    // broadcasts to all subscribers of /topic/example
    @SendTo("/topic/example")
    public ExampleSend example(ExampleReceive received) throws Exception {
        Thread.sleep(1000);
        return new ExampleSend();
    }
}