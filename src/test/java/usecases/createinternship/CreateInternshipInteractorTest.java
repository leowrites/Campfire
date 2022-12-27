package usecases.createinternship;
import entity.Corporate;
import entity.Internship;
import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.ServerStatus;
import service.dao.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CreateInternshipInteractorTest {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private ICorporateDAO corporateDAO;
    @Autowired
    private IInternshipDAO internshipDAO;

    @Autowired
    CreateInternshipInputBoundary internshipCreator;

    @BeforeEach
    public void init() {
        internshipCreator = new CreateInternshipInteractor(
                internshipDAO, userDAO, corporateDAO);
    }

    @Test
    @Transactional
    public void testCreateInternshipInteractorWithCorporateRep() {
        User user = new User();
        user.setCorporateRep(true);
        user.setUsername("leo");

        Corporate corporate = new Corporate(
                user,
                "Apple",
                "description"
        );
        Corporate savedCorporate = corporateDAO.save(corporate);

        CreateInternshipInputDS inputDS = new CreateInternshipInputDS(
                "test job",
                savedCorporate.getId(),
                user.getUsername()
        );
        CreateInternshipResponseDS response = internshipCreator.createInternship(inputDS);
        assertEquals(ServerStatus.SUCCESS, response.getServerStatus());

        List<Internship> savedInternships = internshipDAO.getInternshipByCompanyId(savedCorporate.getId());
        assertEquals(1, savedInternships.size());

        Internship actualInternship = savedInternships.get(0);
        assertEquals(actualInternship.getCreator(), user.getUsername());
    }

    @Test
    @Transactional
    public void testCreateInternshipInteractorWithNoAccess() {
        User user = new User();
        user.setCorporateRep(false);
        user.setUsername("leo");

        Corporate corporate = new Corporate(
                user,
                "Apple",
                "description"
        );
        Corporate savedCorporate = corporateDAO.save(corporate);

        CreateInternshipInputDS inputDS = new CreateInternshipInputDS(
                "test job",
                savedCorporate.getId(),
                user.getUsername()
        );
        CreateInternshipResponseDS response = internshipCreator.createInternship(inputDS);
        assertEquals(ServerStatus.ERROR, response.getServerStatus());
        assertEquals("not authorized to create new internship", response.getMessage());

        List<Internship> savedInternships = internshipDAO.getInternshipByCompanyId(savedCorporate.getId());
        assertEquals(0, savedInternships.size());
    }

    @Test
    @Transactional
    public void testCreateInternshipCatchUserNotFoundError() {
        User user = new User();
        user.setCorporateRep(true);
        user.setUsername("leo");

        Corporate corporate = new Corporate(
                user,
                "Apple",
                "description"
        );
        Corporate savedCorporate = corporateDAO.save(corporate);

        CreateInternshipInputDS inputDS = new CreateInternshipInputDS(
                "test job",
                savedCorporate.getId(),
                "not leo"
        );
        CreateInternshipResponseDS response = internshipCreator.createInternship(inputDS);
        assertEquals(ServerStatus.ERROR, response.getServerStatus());
        assertEquals("No user with name not leo found", response.getMessage());

        List<Internship> savedInternships = internshipDAO.getInternshipByCompanyId(savedCorporate.getId());
        assertEquals(0, savedInternships.size());
    }
}
