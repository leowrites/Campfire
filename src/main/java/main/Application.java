package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import user.requestconnect.IRequestConnectionDataAccess;
import user.acceptconnect.IAcceptConnectionDataAccess;



@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"user"})
public class Application{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IAcceptConnectionDataAccess dataAccess;

    @Autowired
    private IRequestConnectionDataAccess data;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}