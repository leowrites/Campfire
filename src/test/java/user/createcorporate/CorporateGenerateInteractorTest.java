package user.createcorporate;

import entity.Corporate;
import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.ServerStatus;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import user.createcorporate.exceptions.CompanyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CorporateGenerateInteractorTest {

    @Autowired
    private ICorporateDAO corporateDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CorporateGenerateInteractor interactor;
    private CorporateFactory corporateFactory;

    @BeforeEach
    public void init() {
        corporateFactory = new CorporateFactory();
        interactor = new CorporateGenerateInteractor(corporateDAO, userDAO, corporateFactory);
        jdbcTemplate.execute("DROP TABLE IF EXISTS corporates");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS corporates (id serial primary key, company varchar UNIQUE, data varchar)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (username varchar(50) primary key, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM corporates");
        jdbcTemplate.execute("DELETE FROM users");
    }

    @Test
    public void testCreateCorporatePageIfUserIsCorporateRepAndCompanyNameUnique() {
        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        rep.setCorporateRep(true);
        userDAO.saveUser(rep);
        CorporateGenerateRequestModel requestModel = new CorporateGenerateRequestModel("justinli",
                "Apple", "Founded by Steve Jobs.");
        CorporateGenerateResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a successful response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Corporate page created successfully!", responseModel.getMessage());
        assertEquals(1, responseModel.getCorporateId());
        // test that the corporate page was properly saved in the corporates table
        Corporate corporate;
        try {
            corporate = corporateDAO.getCorporateByName("Apple");
        }
        catch (CompanyNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Apple", corporate.getCompanyName());
        assertEquals("Founded by Steve Jobs.", corporate.getCompanyInfo());
        assertEquals("jli@mail.utoronto.ca", rep.getEmail());
        assertEquals("justinli", rep.getUsername());
    }

    @Test
    public void testCreateCorporatePageIfUserIsNotCorporateRep() {
        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        userDAO.saveUser(rep);
        CorporateGenerateRequestModel requestModel = new CorporateGenerateRequestModel("justinli",
                "Apple", "Founded by Steve Jobs.");
        CorporateGenerateResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a failure response model
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("User is not a company rep.", responseModel.getMessage());
        assertEquals(0, responseModel.getCorporateId());
        // test that there is nothing saved in the corporates table
        boolean exists = corporateDAO.companyExists("Apple");
        assertEquals(false, exists);
    }

    @Test
    public void testCreateCorporatePageIfCompanyNameNotUnique() {
        User justin = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        Corporate corporate = new Corporate("justinli", "Apple", "Founded by Steve Jobs.");
        corporateDAO.saveCorporate(corporate);
        User leo = new User("leoliu", "leo@mail.utoronto.ca", "password", "Leo");
        leo.setCorporateRep(true);
        userDAO.saveUser(leo);
        CorporateGenerateRequestModel requestModel = new CorporateGenerateRequestModel("leoliu",
                "Apple", "Founded by Bill Gates.");
        CorporateGenerateResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a failure response model
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("Company already exists.", responseModel.getMessage());
        assertEquals(0, responseModel.getCorporateId());
        // test that the company in the database is the original company
        try {
            corporate = corporateDAO.getCorporateByName("Apple");
        }
        catch (CompanyNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Founded by Steve Jobs.", corporate.getCompanyInfo());
        assertEquals("jli@mail.utoronto.ca", justin.getEmail());
        assertEquals("justinli", justin.getUsername());
    }
}
