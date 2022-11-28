package user.signup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.IUserDataAccess;


@Configuration
@ComponentScan("service")
public class SignUpConfig {
    @Bean
    public SignUpInputBoundary SignUpInput(IUserDataAccess dataAccess){
        return new SignUpInteractor(dataAccess);
    }

}
