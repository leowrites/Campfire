package user.requestconnect;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public IRequestConnectionDataAccess connectionDataAccess() {
        return new RequestConnectionDataAccess();
    }

    @Bean
    public IRequestConnectionInput connectionInput(@Qualifier("connectionDataAccess")
                                                       IRequestConnectionDataAccess dataAccess) {
        return new RequestConnectionInteractor(dataAccess);
    }
}
