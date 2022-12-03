package user.createinternship;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;

@Configuration
@ComponentScan("service")

public class CreateInternshipConfig {
    @Bean
    @Primary
    public CreateInternshipInputBoundary createInternshipInput(IInternshipDAO internshipDataAccess,
                                                               IUserDAO userDataAccess){
        return new CreateInternshipInteractor(internshipDataAccess, userDataAccess);
    }
}
