package usecases.createcorporate;

import entity.Corporate;
import entity.User;
import main.Application;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.ServerStatus;
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import usecases.createcorporate.exceptions.CompanyNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CorporateGenerateInteractorTest {

    @Autowired
    private ICorporateDAO corporateDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private CorporateFactory corporateFactory;

    private CorporateGenerateInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new CorporateGenerateInteractor(corporateDAO, userDAO, corporateFactory);
    }

    @Test
    @Transactional
    public void testCreateCorporatePageIfUserIsCorporateRepAndCompanyNameUnique() {
        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        rep.setCorporateRep(true);
        userDAO.save(rep);
        CorporateGenerateRequestModel requestModel = new CorporateGenerateRequestModel("justinli",
                "Apple", "Founded by Steve Jobs.");
        CorporateGenerateResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a successful response model
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("Corporate page created successfully!", responseModel.getMessage());
        assertNotNull( responseModel.getCorporateId());
        // test that the corporate page was properly saved in the corporates table
        Corporate corporate;
        try {
            corporate = corporateDAO.get("Apple");
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
    @Transactional
    public void testCreateCorporatePageIfUserIsNotCorporateRep() {
        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        userDAO.save(rep);
        CorporateGenerateRequestModel requestModel = new CorporateGenerateRequestModel("justinli",
                "Apple", "Founded by Steve Jobs.");
        CorporateGenerateResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a failure response model
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("User is not a company rep.", responseModel.getMessage());
        assertNull(responseModel.getCorporateId());
        // test that there is nothing saved in the corporates table
        boolean exists = corporateDAO.companyExists("Apple");
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testCreateCorporatePageIfCompanyNameNotUnique() {
        User justin = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        Corporate corporate = new Corporate(justin, "Apple", "Founded by Steve Jobs.");
        corporateDAO.save(corporate);
        User leo = new User("leoliu", "leo@mail.utoronto.ca", "password", "Leo");
        leo.setCorporateRep(true);
        userDAO.save(leo);
        CorporateGenerateRequestModel requestModel = new CorporateGenerateRequestModel("leoliu",
                "Apple", "Founded by Bill Gates.");
        CorporateGenerateResponseModel responseModel = interactor.create(requestModel);
        // test that the interactor returns a failure response model
        assertEquals(ServerStatus.ERROR, responseModel.getStatus());
        assertEquals("Company already exists.", responseModel.getMessage());
        assertNull(responseModel.getCorporateId());
        // test that the company in the database is the original company
        try {
            corporate = corporateDAO.get("Apple");
        }
        catch (CompanyNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Founded by Steve Jobs.", corporate.getCompanyInfo());
        assertEquals("jli@mail.utoronto.ca", justin.getEmail());
        assertEquals("justinli", justin.getUsername());
    }
}
