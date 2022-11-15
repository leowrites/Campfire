package user.requestconnect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public IRequestConnectionInput connectionInput(IRequestConnectionDataAccess dataAccess) {
        return new RequestConnectionInteractor(dataAccess);
    }
}
