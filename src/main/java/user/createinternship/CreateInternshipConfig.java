package user.createinternship;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;

@Configuration
@ComponentScan("service")

public class CreateInternshipConfig {
    @Bean
    @Primary
    public CreateInternshipInputBoundary createInternshipInput(IInternshipDAO internshipDataAccess,
                                                               IUserDAO userDataAccess,
                                                               ICorporateDAO corporateDAO){
        return new CreateInternshipInteractor(internshipDataAccess, userDataAccess, corporateDAO);
    }
}
