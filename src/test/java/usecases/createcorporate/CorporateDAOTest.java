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
import service.dao.ICorporateDAO;
import service.dao.IUserDAO;
import usecases.createcorporate.exceptions.CompanyNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CorporateDAOTest {

    @Autowired
    private ICorporateDAO corporateDAO;

    @Test
    @Transactional
    public void testSaveCorporateAndGetCorporate(){
        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        Corporate corporate = new Corporate(rep, "Apple", "Founded by Steve Jobs.");
        Corporate savedCorporate = corporateDAO.save(corporate);
        assertNotNull(savedCorporate.getId());
        try {
            Corporate dbCorporate = corporateDAO.get(corporate.getCompanyName());
            assertThat(dbCorporate).usingRecursiveComparison().isEqualTo(corporate);
        }
        catch (CompanyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    public void testCompanyExistsForCompanyThatExists() {
        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        Corporate corporate = new Corporate(rep, "Apple", "Founded by Steve Jobs.");
        corporateDAO.save(corporate);
        assertTrue(corporateDAO.companyExists(corporate.getCompanyName()));
    }

    @Test
    @Transactional
    public void testCompanyExistsForCompanyThatDoesNotExist() {
        boolean exists = corporateDAO.companyExists("Banana");
        assertFalse(exists);
    }
}
