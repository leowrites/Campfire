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
import service.dao.CorporateDAO;
import service.dao.ICorporateDAO;
import user.createcorporate.exceptions.CompanyNotFoundException;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CorporateDAOTest {

    @Autowired
    private ICorporateDAO corporateDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS corporates");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS corporates (id serial primary key, company varchar UNIQUE, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM corporates");
    }

    @Test
    public void testSaveCorporateAndGetCorporate(){
//        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        Corporate corporate = new Corporate("justinli", "Apple", "Founded by Steve Jobs.");
        int corporateId = corporateDAO.saveCorporate(corporate);
        corporate.setId(corporateId);
        assertEquals(1, corporateId);
        try {
            Corporate dbCorporate = corporateDAO.getCorporate(corporate.getCompanyName());
            assertThat(dbCorporate).usingRecursiveComparison().isEqualTo(corporate);
        }
        catch (CompanyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetCorporatesWithSubstring(){
        Corporate corporate = new Corporate("testRep", "test", "Test company");
        int save = corporateDAO.saveCorporate(corporate);
        assertEquals(1, save);
        try {
            ArrayList<Corporate> corporateList = corporateDAO.getCorporatesWithSubstring("t");

            assertEquals(corporateList.get(0).getRep(), "testRep");
            assertEquals(corporateList.get(0).getCompanyName(), "test");
            assertEquals(corporateList.get(0).getCompanyInfo(), "Test company");

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw(e);
        }
    }

    @Test
    public void testCompanyExistsForCompanyThatExists() {
        User rep = new User("justinli", "jli@mail.utoronto.ca", "password", "Justin");
        Corporate corporate = new Corporate("justinli", "Apple", "Founded by Steve Jobs.");
        corporateDAO.saveCorporate(corporate);
        boolean exists = corporateDAO.companyExists(corporate.getCompanyName());
        assertEquals(true, exists);
    }

    @Test
    public void testCompanyExistsForCompanyThatDoesNotExist() {
        boolean exists = corporateDAO.companyExists("Banana");
        assertEquals(false, exists);
    }
}
