package usecases.createinternship;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import service.dao.IInternshipDAO;

/** A configuration class for Spring that gets a CreateInternshipInteractor. It is connected
 * to the Autowired constructor in the CreateInternshipController class.
 */
@Configuration
@ComponentScan("service")

public class CreateInternshipConfig {

    /** Retrieves a new CreateInternshipInteractor, as searched for by the Autowired
     * CreateInternshipController constructor.
     * @param internshipDataAccess an object that implements IInternshipDAO to access the
     *                             internship database
     * @param userDataAccess an object that implements IUserDAO to acces the user database
     * @return a new CreateInternshipInteractor
     */
    @Bean
    @Primary
    public CreateInternshipInputBoundary createInternshipInput(IInternshipDAO internshipDataAccess,
                                                               IUserDAO userDataAccess,
                                                               ICorporateDAO corporateDAO){
        return new CreateInternshipInteractor(internshipDataAccess, userDataAccess, corporateDAO);
    }
}
