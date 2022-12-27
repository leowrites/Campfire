package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaRepositories("service")
@ComponentScan(basePackages = {"usecases", "service"})
@EntityScan("entity")
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("We are up and running\uD83E\uDD75\uD83E\uDD75");
    }

}