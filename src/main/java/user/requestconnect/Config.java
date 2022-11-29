package user.requestconnect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.IUserDataAccess;

@Configuration
@ComponentScan("service")
public class Config {

    @Bean
    public IRequestConnectionInput connectionInput(IUserDataAccess dataAccess) {
        return new RequestConnectionInteractor(dataAccess);
    }
}
