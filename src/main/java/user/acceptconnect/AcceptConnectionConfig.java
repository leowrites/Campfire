package user.acceptconnect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.IUserDataAccess;

@Configuration
@ComponentScan("service")
public class AcceptConnectionConfig {

    @Bean
    public IAcceptConnectionInput acceptConnectionInput(IUserDataAccess dataAccess) {
        return new AcceptConnectionInteractor(dataAccess);
    }
}
