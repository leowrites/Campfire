package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"user"})
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("test");
    }

}