package user.searchcorporate;

import entity.Comment;
import entity.Corporate;
import entity.Review;
import main.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import service.ServerStatus;
import service.dao.ICorporateDAO;
import user.comment.CommentInteractor;
import user.comment.CommentRequestModel;
import user.comment.CommentResponseModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SearchCorporateInteractorTest {

    @Autowired
    private ICorporateDAO corporateDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SearchCorporateInteractor interactor;

    @BeforeEach
    public void init() {
        interactor = new SearchCorporateInteractor(corporateDAO);
        jdbcTemplate.execute("DROP TABLE IF EXISTS corporates");
        jdbcTemplate.execute("CREATE TABLE corporates (id serial primary key, company varchar, data varchar)");
    }

    @AfterEach
    public void cleanUp() {
        jdbcTemplate.execute("DELETE FROM corporates");
    }

    @Test
    public void testSearch() {
        Corporate testCorporate = new Corporate("test", "testCompany", "Test Company");

        this.corporateDAO.saveCorporate(testCorporate);

        // test for whether a search returns a company searched for
        SearchCorporateRequestModel requestModel = new SearchCorporateRequestModel("test");
        SearchCorporateResponseModel responseModel = interactor.search(requestModel);
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        ArrayList<String> companyNames = new ArrayList<>();
        companyNames.add("testCompany");
        assertEquals(companyNames, responseModel.getList_of_companies());

        // test that response model contains "No companies found" message if search term doesn't match anything
        SearchCorporateRequestModel requestModel2 = new SearchCorporateRequestModel("sakdfj");
        SearchCorporateResponseModel responseModel2 = interactor.search(requestModel2);
        // test that the response model contains no companies:
        assertEquals(ServerStatus.SUCCESS, responseModel.getStatus());
        assertEquals("No companies found", responseModel2.getMessage());
    }

}
