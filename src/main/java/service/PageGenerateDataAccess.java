package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CorporatePage;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.createCorporatePage.exceptions.PageCreationFailedException;

@Repository
public class PageGenerateDataAccess implements IPageGenerateDataAccess{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY =
            "INSERT INTO corporate_pages (PageName, UserID, CompanyName, CompanyInfo) values (?, ?, ?, ?)";

    /**
     * save a new page object to db
     * @param page a Page object
     */

    @Override
    public void createPage(Page page) throws PageCreationFailedException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String pageNameString = mapper.writeValueAsString(page);
            jdbcTemplate.update(INSERT_QUERY, page.getPageLabel(), page.getPageOwner());

        } catch (JsonProcessingException e) {
            System.out.println("Json process error!");
        }

    }

    //this is an overload for corporatePage
    public void createPage(CorporatePage corporatePage) throws PageCreationFailedException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String pageNameString = mapper.writeValueAsString(corporatePage);
            jdbcTemplate.update(INSERT_QUERY, corporatePage.getPageLabel(), corporatePage.getPageOwner(),
                    corporatePage.getCompanyName(), corporatePage.getCompanyInfo());

        } catch (JsonProcessingException e) {
            System.out.println("Json process error!");
        }

    }
}
