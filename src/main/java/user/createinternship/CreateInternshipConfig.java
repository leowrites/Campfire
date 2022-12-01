package user.createinternship;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import service.IUserDataAccess;
import service.InternshipDBGateway;

@Configuration
@ComponentScan("service")

public class CreateInternshipConfig {
    @Bean
    @Primary
    public CreateInternshipInputBoundary createInternshipInput(InternshipDBGateway internshipDataAccess,
                                                               IUserDataAccess userDataAccess){
        return new CreateInternshipInteractor(internshipDataAccess, userDataAccess);
    }
}
